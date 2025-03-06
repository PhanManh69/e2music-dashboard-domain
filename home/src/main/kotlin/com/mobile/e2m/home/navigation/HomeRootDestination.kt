package com.mobile.e2m.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mobile.e2m.core.datasource.remote.firebase.data.entity.SongsEntity
import com.mobile.e2m.core.ui.navigation.route.AppNavigationRoute
import com.mobile.e2m.home.presentation.HomeRootScreen

fun NavGraphBuilder.homeRootDestination(
    checkMiniPlayer: Boolean = false,
    menuOnClick: () -> Unit = { },
    playSongOnClick: (SongsEntity) -> Unit = { },
) {
    composable<AppNavigationRoute.Dashboard.Home> {
        HomeRootScreen(
            checkMiniPlayer = checkMiniPlayer,
            menuOnClick = { menuOnClick() },
            playSongOnClick = { playSongOnClick(it) },
        )
    }
}
