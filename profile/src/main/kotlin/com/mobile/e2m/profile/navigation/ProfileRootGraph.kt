package com.mobile.e2m.profile.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.mobile.e2m.profile.presentation.profile.profileDestination

fun NavGraphBuilder.profileRootNavGraph(
    navController: NavHostController,
    checkMiniPlayer: Boolean = false,
    menuOnClick: () -> Unit = { },
    signOutOnClick: () -> Unit = { },
) {
    profileDestination(
        checkMiniPlayer = checkMiniPlayer,
        menuOnClick = { menuOnClick() },
        signOutOnClick = { signOutOnClick() },
    )
}
