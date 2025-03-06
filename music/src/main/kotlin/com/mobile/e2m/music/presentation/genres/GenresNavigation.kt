package com.mobile.e2m.music.presentation.genres

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.mobile.e2m.core.ui.navigation.route.AppNavigationRoute

internal fun NavController.goToGenres(navOptions: NavOptions? = null) {
    this.navigate(route = AppNavigationRoute.Dashboard.Music.Genre, navOptions)
}

internal fun NavGraphBuilder.genresDestination(
    checkMiniPlayer: Boolean = false,
) {
    composable<AppNavigationRoute.Dashboard.Music.Genre> {
        GenresScreen(
            checkMiniPlayer = checkMiniPlayer,
        )
    }
}
