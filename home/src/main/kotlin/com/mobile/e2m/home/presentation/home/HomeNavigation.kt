package com.mobile.e2m.home.presentation.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.mobile.e2m.core.datasource.remote.firebase.data.entity.SongsEntity
import com.mobile.e2m.core.ui.navigation.route.AppNavigationRoute

internal fun NavController.goToHome(navOptions: NavOptions? = null) {
    this.navigate(route = AppNavigationRoute.Dashboard.Home, navOptions)
}

internal fun NavGraphBuilder.homeDestination(
    checkMiniPlayer: Boolean = false,
    menuOnClick: () -> Unit = { },
    playSongOnClick: (SongsEntity) -> Unit = { },
) {
    composable<AppNavigationRoute.Dashboard.Home> {
        HomeScreen(
            checkMiniPlayer = checkMiniPlayer,
            menuOnClick = { menuOnClick() },
            playSongOnClick = { playSongOnClick(it) },
        )
    }
}
