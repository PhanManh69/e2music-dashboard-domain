package com.mobile.e2m.music.presentation.artists

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.mobile.e2m.core.ui.navigation.route.AppNavigationRoute

internal fun NavController.goToArtists(navOptions: NavOptions? = null) {
    this.navigate(route = AppNavigationRoute.Dashboard.Music.Artist, navOptions)
}

internal fun NavGraphBuilder.artistsDestination(
    checkMiniPlayer: Boolean = false,
) {
    composable<AppNavigationRoute.Dashboard.Music.Artist> {
        ArtistsScreen(
            checkMiniPlayer = checkMiniPlayer,
        )
    }
}
