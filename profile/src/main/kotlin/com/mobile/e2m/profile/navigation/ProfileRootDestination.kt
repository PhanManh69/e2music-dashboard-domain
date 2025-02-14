package com.mobile.e2m.profile.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mobile.e2m.core.ui.navigation.route.AppNavigationRoute
import com.mobile.e2m.profile.presentation.ProfileRootScreen

fun NavGraphBuilder.profileRootDestination() {
    composable<AppNavigationRoute.Dashboard.Profile> {
        ProfileRootScreen()
    }
}
