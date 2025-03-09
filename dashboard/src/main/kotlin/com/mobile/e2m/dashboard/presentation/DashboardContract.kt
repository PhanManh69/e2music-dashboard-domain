package com.mobile.e2m.dashboard.presentation

import com.mobile.e2m.core.datasource.remote.firebase.data.entity.SongsEntity

sealed interface DashboardAction {
    data object PlayPauseClick : DashboardAction
    data object NextClick : DashboardAction
    data object PreviousClick : DashboardAction
    data class SongSelectedClick(val song: SongsEntity) : DashboardAction
}

data class DashboardState(
    val isLoading: Boolean = false,
    val isPlaying: Boolean = false,
    val currentDuration: String? = null,
    val maxDuration: String? = null,
    val currentSong: SongsEntity? = null,
    val songsList: List<SongsEntity>? = null,
)
