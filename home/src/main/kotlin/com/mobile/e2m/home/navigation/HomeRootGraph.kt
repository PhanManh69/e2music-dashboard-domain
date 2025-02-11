package com.mobile.e2m.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.mobile.e2m.home.presentation.home.homeDestination

fun NavGraphBuilder.homeRootNavGraph(
    navController: NavHostController,
) {
    homeDestination()
}
