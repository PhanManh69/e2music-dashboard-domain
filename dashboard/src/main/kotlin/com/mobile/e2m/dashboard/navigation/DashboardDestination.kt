package com.mobile.e2m.dashboard.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mobile.e2m.core.ui.navigation.route.AppNavigationRoute
import com.mobile.e2m.dashboard.presentation.DashboardScreen

fun NavGraphBuilder.dashboardDestination() {
    composable<AppNavigationRoute.Dashboard> {
        DashboardScreen()
    }
}
