package com.mobile.e2m.music.presentation.songs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.mobile.e2m.core.ui.navigation.route.AppNavigationRoute

internal fun NavController.goToSongs(navOptions: NavOptions? = null) {
    this.navigate(route = AppNavigationRoute.Dashboard.Music.Song, navOptions)
}

internal fun NavGraphBuilder.songsDestination() {
    composable<AppNavigationRoute.Dashboard.Music.Song> {
        SongsScreen()
    }
}
