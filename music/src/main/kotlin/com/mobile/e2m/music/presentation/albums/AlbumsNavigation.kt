package com.mobile.e2m.music.presentation.albums

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.mobile.e2m.core.ui.navigation.route.AppNavigationRoute

internal fun NavController.goToAlbums(navOptions: NavOptions? = null) {
    this.navigate(route = AppNavigationRoute.Dashboard.Music.Album, navOptions)
}

internal fun NavGraphBuilder.albumsDestination(
    checkMiniPlayer: Boolean = false,
) {
    composable<AppNavigationRoute.Dashboard.Music.Album> {
        AlbumsScreen(
            checkMiniPlayer = checkMiniPlayer,
        )
    }
}
