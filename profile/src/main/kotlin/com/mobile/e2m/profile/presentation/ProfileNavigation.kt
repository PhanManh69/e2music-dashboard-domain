package com.mobile.e2m.profile.presentation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.mobile.e2m.core.ui.navigation.route.AppNavigationRoute

internal fun NavController.goToProfile(navOptions: NavOptions? = null) {
    this.navigate(route = AppNavigationRoute.Dashboard.Profile, navOptions)
}

fun NavGraphBuilder.profileDestination(navController: NavController) {
    composable<AppNavigationRoute.Dashboard.Profile> {
        ProfileScreen()
    }
}
