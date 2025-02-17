package com.mobile.e2m.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mobile.e2m.core.ui.navigation.route.AppNavigationRoute
import com.mobile.e2m.home.presentation.HomeRootScreen

fun NavGraphBuilder.homeRootDestination(
    menuOnClick: () -> Unit = { },
) {
    composable<AppNavigationRoute.Dashboard.Home> {
        HomeRootScreen(
            menuOnClick = { menuOnClick() }
        )
    }
}
