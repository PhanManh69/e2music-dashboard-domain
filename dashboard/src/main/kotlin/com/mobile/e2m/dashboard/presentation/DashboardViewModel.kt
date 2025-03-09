package com.mobile.e2m.dashboard.presentation

import android.app.Application
import android.content.ComponentName
import android.content.Intent
import android.util.Log
import androidx.annotation.OptIn
import androidx.lifecycle.viewModelScope
import androidx.media3.common.C
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.session.MediaController
import androidx.media3.session.SessionToken
import com.mobile.e2m.core.datasource.remote.firebase.data.SongsFirebaseDataSource
import com.mobile.e2m.core.datasource.remote.firebase.data.entity.SongsEntity
import com.mobile.e2m.core.ui.base.E2MBaseViewModel
import com.mobile.e2m.home.domain.usecase.GetSongsUseCase
import com.mobile.e2m.service.exoplayer.MusicService
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.guava.await
import kotlinx.coroutines.launch

@OptIn(UnstableApi::class)
class DashboardViewModel(
    private val songsFirebaseDataSource: SongsFirebaseDataSource,
    private val getSongsUseCase: GetSongsUseCase,
    private val application: Application,
) : E2MBaseViewModel<DashboardState, Unit, DashboardAction>(
    initialState = DashboardState()
) {

    private var mediaController: MediaController? = null
    private var isFirstLaunch = true

    init {
        getSongsInfo()
        initializeMediaController()
    }

    override fun handleAction(action: DashboardAction) {
        when (action) {
            DashboardAction.PlayPauseClick -> handlePlayPause()
            DashboardAction.NextClick -> handleNext()
            DashboardAction.PreviousClick -> handlePrevious()
            is DashboardAction.SongSelectedClick -> handleSongSelected(action.song)
        }
    }

    override fun onCleared() {
        super.onCleared()

        mediaController?.release()
        mediaController = null

        val indent = Intent(application, MusicService::class.java)
        application.stopService(indent)
    }

    @OptIn(UnstableApi::class)
    fun initializeMediaController() {
        viewModelScope.launch {
            if (mediaController != null) return@launch

            val sessionToken = SessionToken(
                application,
                ComponentName(application, MusicService::class.java)
            )

            mediaController = MediaController.Builder(application, sessionToken)
                .buildAsync()
                .await()

            mediaController?.let { controller ->
                controller.addListener(object : Player.Listener {
                    override fun onIsPlayingChanged(isPlaying: Boolean) {
                        mutableStateFlow.update { it.copy(isPlaying = isPlaying) }
                    }

                    override fun onMediaItemTransition(mediaItem: MediaItem?, reason: Int) {
                        if (!isFirstLaunch) {
                            playAfterSeek()
                        }
                    }

                    override fun onPlaybackStateChanged(playbackState: Int) {
                        mutableStateFlow.update {
                            it.copy(
                                isPlaying = playbackState == Player.STATE_READY && controller.isPlaying
                            )
                        }
                    }
                })

                viewModelScope.launch {
                    while (true) {
                        if (mediaController != null) {
                            val current =
                                mediaController?.currentPosition?.formatDuration() ?: "00:00"
                            val max = mediaController?.duration?.formatDuration() ?: "00:00"

                            if (current != "00:00" && max != "00:00") {
                                mutableStateFlow.update {
                                    it.copy(
                                        currentDuration = current,
                                        maxDuration = max
                                    )
                                }
                            }
                        }
                        delay(1000L)
                    }
                }
            }
        }
    }

    private fun getSongsInfo() {
        viewModelScope.launch {
            getSongsUseCase.invoke()
                .onStart {
                    Log.d("EManh Debug", "Get Songs Info: OnStart")
                    mutableStateFlow.update { it.copy(isLoading = true) }
                }
                .catch { error ->
                    Log.d("EManh Debug", "Get Songs Info: ${error.message}")
                    mutableStateFlow.update { it.copy(isLoading = false) }
                }
                .collect { result ->
                    result.onSuccess { data ->
                        Log.d("EManh Debug", "Get Songs Info: $data")
                        data.let {
                            mutableStateFlow.update {
                                state.copy(
                                    songsList = data,
                                    isLoading = false,
                                )
                            }
                        }
                    }

                    result.onFailure { error ->
                        Log.d("EManh Debug", "Get Songs Info: ${error.message}")
                        mutableStateFlow.update { it.copy(isLoading = false) }
                    }
                }
        }
    }

    private fun handlePlayPause() {
        mediaController?.let { controller ->
            if (controller.isPlaying) {
                controller.pause()
                mutableStateFlow.update { it.copy(isPlaying = false) }
            } else {
                mutableStateFlow.update { it.copy(isLoading = true) }
                viewModelScope.launch {
                    controller.prepare()
                    controller.play()

                    while (controller.playbackState != Player.STATE_READY) {
                        delay(100)
                    }

                    mutableStateFlow.update {
                        it.copy(
                            isLoading = false,
                            isPlaying = true,
                        )
                    }
                }
            }
        }
    }

    private fun handleSongSelected(song: SongsEntity) {
        isFirstLaunch = false
        val songIndex = state.songsList?.indexOfFirst { it.id == song.id }
        if (songIndex != null) {
            mediaController?.seekTo(songIndex, 0)
        }
        playAfterSeek()
    }

    private fun handleNext() {
        mediaController?.seekToNext()
        mediaController?.play()
        playAfterSeek()
    }

    private fun handlePrevious() {
        mediaController?.seekToPrevious()
        mediaController?.play()
        playAfterSeek()
    }

    @OptIn(UnstableApi::class)
    private fun playAfterSeek() {
        viewModelScope.launch {
            mutableStateFlow.update {
                it.copy(
                    isLoading = true,
                    currentDuration = "00:00",
                    maxDuration = "00:00"
                )
            }

            while (mediaController?.currentMediaItem == null || mediaController?.duration == C.TIME_UNSET) {
                delay(100)
            }

            mutableStateFlow.update {
                it.copy(
                    currentSong = songsFirebaseDataSource.getSongByMediaItem(mediaController?.currentMediaItem),
                    maxDuration = mediaController?.duration?.formatDuration() ?: "00:00",
                    currentDuration = mediaController?.currentPosition?.formatDuration() ?: "00:00",
                )
            }

            mediaController?.play()

            mutableStateFlow.update {
                it.copy(
                    isPlaying = true,
                    isLoading = false,
                )
            }
        }
    }

    private fun Long.formatDuration(): String {
        val minutes = (this / 1000) / 60
        val seconds = (this / 1000) % 60
        return "%02d:%02d".format(minutes, seconds)
    }
}
