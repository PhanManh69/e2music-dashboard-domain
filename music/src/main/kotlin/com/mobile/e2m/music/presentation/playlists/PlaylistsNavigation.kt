package com.mobile.e2m.music.presentation.playlists

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.mobile.e2m.core.ui.navigation.route.AppNavigationRoute

internal fun NavController.goToPlaylists(navOptions: NavOptions? = null) {
    this.navigate(route = AppNavigationRoute.Dashboard.Music.Playlist, navOptions)
}

internal fun NavGraphBuilder.playlistsDestination() {
    composable<AppNavigationRoute.Dashboard.Music.Playlist> {
        PlaylistsScreen()
    }
}
