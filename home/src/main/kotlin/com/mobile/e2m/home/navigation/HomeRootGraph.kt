package com.mobile.e2m.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.mobile.e2m.core.datasource.remote.firebase.data.entity.SongsEntity
import com.mobile.e2m.home.presentation.home.homeDestination

fun NavGraphBuilder.homeRootNavGraph(
    navController: NavHostController,
    checkMiniPlayer: Boolean = false,
    menuOnClick: () -> Unit = { },
    playSongOnClick: (SongsEntity) -> Unit = { },
) {
    homeDestination(
        checkMiniPlayer = checkMiniPlayer,
        menuOnClick = { menuOnClick() },
        playSongOnClick = { playSongOnClick(it) },
    )
}
