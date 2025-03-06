@file:OptIn(ExperimentalMaterial3Api::class)

package com.mobile.e2m.home.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mobile.e2m.core.datasource.local.fakedata.model.AlbumsModel
import com.mobile.e2m.core.datasource.remote.firebase.data.entity.SongsEntity
import com.mobile.e2m.core.ui.composable.scaffold.E2MScaffold
import com.mobile.e2m.core.ui.composable.background.E2MBackgroundDark
import com.mobile.e2m.core.ui.theme.E2MTheme
import com.mobile.e2m.core.ui.util.EventsEffect
import com.mobile.e2m.home.presentation.getString
import com.mobile.e2m.home.presentation.home.HomeConfig.NUMBER_LOADING
import com.mobile.e2m.home.presentation.home.composable.HomeHeader
import com.mobile.e2m.home.presentation.home.composable.HomeRecentlySongsItem
import com.mobile.e2m.home.presentation.home.composable.HomeRecentlySongsLoad
import com.mobile.e2m.home.presentation.home.composable.HomeRecommendAlbum
import com.mobile.e2m.home.presentation.home.composable.HomeRecommendAlbumData
import com.mobile.e2m.home.presentation.home.composable.HomeRecommendSong
import com.mobile.e2m.home.presentation.home.composable.HomeRecommendSongData
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun HomeScreen(
    checkMiniPlayer: Boolean = false,
    menuOnClick: () -> Unit = { },
    playSongOnClick: (SongsEntity) -> Unit = { },
    viewModel: HomeViewModel = koinViewModel()
) {
    val state by viewModel.stateFlow.collectAsStateWithLifecycle()

    HomeScaffold(
        checkMiniPlayer = checkMiniPlayer,
        isLoadingSong = state.isLoadingSong,
        albumsList = state.albumsList,
        songsList = state.songsList,
        menuOnClick = { menuOnClick() },
        playSongOnClick = { playSongOnClick(it) },
    )
}

@Composable
private fun HomeScaffold(
    modifier: Modifier = Modifier,
    checkMiniPlayer: Boolean = false,
    isLoadingSong: Boolean = false,
    menuOnClick: () -> Unit = { },
    albumsList: List<AlbumsModel>,
    songsList: List<SongsEntity>,
    playSongOnClick: (SongsEntity) -> Unit = { },
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        E2MBackgroundDark()

        E2MScaffold(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                HomeHeader(
                    scrollBehavior = scrollBehavior,
                    menuOnClick = { menuOnClick() }
                )
            },
            content = {
                HomeContent(
                    checkMiniPlayer = checkMiniPlayer,
                    isLoadingSong = isLoadingSong,
                    albumsList = albumsList,
                    songsList = songsList,
                    playSongOnClick = { playSongOnClick(it) },
                )
            }
        )
    }
}

@Composable
private fun HomeContent(
    modifier: Modifier = Modifier,
    checkMiniPlayer: Boolean = false,
    isLoadingSong: Boolean = false,
    albumsList: List<AlbumsModel>,
    songsList: List<SongsEntity>,
    playSongOnClick: (SongsEntity) -> Unit = { },
    favouriteSongOnClick: () -> Unit = { },
) {
    val size = E2MTheme.alias.size
    val color = E2MTheme.alias.color
    val style = E2MTheme.typography

    var currentSongPlaying by remember { mutableIntStateOf(-1) }

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            top = size.spacing.large,
            bottom = if (checkMiniPlayer) size.spacing.large9x else size.spacing.large,
        ),
    ) {
        item {
            HomeRecommendAlbum(
                items = albumsList.map {
                    HomeRecommendAlbumData(
                        imageUrl = it.avatar,
                        title = it.name,
                    )
                }
            )
        }

        item {
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = size.spacing.small,
                        vertical = size.spacing.large,
                    ),
                thickness = size.stroke.thick,
                color = color.border.blackLight
            )
        }

        item {
            HomeRecommendSong(
                isLoading = isLoadingSong,
                items = songsList.map {
                    HomeRecommendSongData(
                        imageUrl = it.imageUrl,
                        name = it.name,
                        singer = it.singer,
                    )
                }
            )
        }

        item {
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = size.spacing.small,
                        vertical = size.spacing.large,
                    ),
                thickness = size.stroke.thick,
                color = color.border.blackLight
            )
        }

        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = size.spacing.small)
                    .padding(bottom = size.spacing.small),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = getString().recentlyPlayedTxt,
                    style = style.title.bold,
                    color = color.text.white,
                )

                Text(
                    text = getString().playedAllTxt,
                    style = style.base.bold,
                    color = color.text.blur2Light,
                )
            }
        }

        if (isLoadingSong) {
            items(NUMBER_LOADING) {
                HomeRecentlySongsLoad(
                    loadIndex = it,
                    numberLoad = NUMBER_LOADING,
                )
            }
        } else {
            items(songsList.size) { index ->
                HomeRecentlySongsItem(
                    songIndex = index,
                    songsList = songsList,
                    isCurrentPlaying = index == currentSongPlaying,
                    playSongOnClick = {
                        playSongOnClick(it)
                        currentSongPlaying = index
                    },
                    favouriteSongOnClick = { favouriteSongOnClick() },
                )
            }
        }
    }
}
