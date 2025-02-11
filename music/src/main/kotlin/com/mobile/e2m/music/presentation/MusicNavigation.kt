package com.mobile.e2m.music.presentation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.mobile.e2m.core.ui.navigation.route.AppNavigationRoute

internal fun NavController.goToMusic(navOptions: NavOptions? = null) {
    this.navigate(route = AppNavigationRoute.Dashboard.Music, navOptions)
}

fun NavGraphBuilder.musicDestination(navController: NavController) {
    composable<AppNavigationRoute.Dashboard.Music> {
        MusicScreen()
    }
}

