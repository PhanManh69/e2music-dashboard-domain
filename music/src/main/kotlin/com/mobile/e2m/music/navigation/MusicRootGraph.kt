package com.mobile.e2m.music.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.mobile.e2m.music.presentation.music.musicDestination

fun NavGraphBuilder.musicRootNavGraph(
    navController: NavHostController,
) {
    musicDestination()
}

