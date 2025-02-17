package com.mobile.e2m.profile.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.mobile.e2m.profile.presentation.profile.profileDestination

fun NavGraphBuilder.profileRootNavGraph(
    navController: NavHostController,
    menuOnClick: () -> Unit = { },
) {
    profileDestination(
        menuOnClick = { menuOnClick() }
    )
}
