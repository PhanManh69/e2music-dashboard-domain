package com.mobile.e2m.music.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mobile.e2m.core.ui.navigation.route.AppNavigationRoute
import com.mobile.e2m.music.presentation.MusicRootScreen

fun NavGraphBuilder.musicRootDestination(
    checkMiniPlayer: Boolean = false,
    menuOnClick: () -> Unit = { },
) {
    composable<AppNavigationRoute.Dashboard.Music> {
        MusicRootScreen(
            checkMiniPlayer = checkMiniPlayer,
            menuOnClick = { menuOnClick() }
        )
    }
}
