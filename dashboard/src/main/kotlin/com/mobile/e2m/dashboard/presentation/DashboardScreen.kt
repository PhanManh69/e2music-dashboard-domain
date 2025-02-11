package com.mobile.e2m.dashboard.presentation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.only
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.mobile.e2m.core.ui.composable.E2MScaffold
import com.mobile.e2m.core.ui.navigation.route.AppNavigationRoute
import com.mobile.e2m.dashboard.presentation.composable.DashboardNavButton
import com.mobile.e2m.home.presentation.homeDestination
import com.mobile.e2m.music.presentation.musicDestination
import com.mobile.e2m.profile.presentation.profileDestination

@Composable
internal fun DashboardScreen(
    navController: NavHostController = rememberNavController(),
) {
    DashboardScaffold(
        navController = navController
    )
}

@Composable
internal fun DashboardScaffold(
    navController: NavHostController,
) {
    E2MScaffold(
        bottomBar = {
            DashboardNavButton(navController = navController)
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = AppNavigationRoute.Dashboard.Home,
            modifier = Modifier
                .consumeWindowInsets(WindowInsets.navigationBars.only(WindowInsetsSides.Vertical))
                .consumeWindowInsets(WindowInsets.ime),
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Start, tween(700),
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Start, tween(700),
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.End, tween(700),
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.End, tween(700),
                )
            },
        ) {
            homeDestination(navController)
            musicDestination(navController)
            profileDestination(navController)
        }
    }
}
