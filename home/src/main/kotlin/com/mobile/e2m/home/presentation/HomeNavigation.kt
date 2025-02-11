package com.mobile.e2m.home.presentation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.mobile.e2m.core.ui.navigation.route.AppNavigationRoute

internal fun NavController.goToHome(navOptions: NavOptions? = null) {
    this.navigate(route = AppNavigationRoute.Dashboard.Home, navOptions)
}

fun NavGraphBuilder.homeDestination(navController: NavController) {
    composable<AppNavigationRoute.Dashboard.Home> {
        HomeScreen()
    }
}
