package com.mobile.e2m.home.presentation

import android.content.Context
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.only
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.mobile.e2m.core.datasource.remote.firebase.data.entity.SongsEntity
import com.mobile.e2m.core.ui.navigation.route.AppNavigationRoute
import com.mobile.e2m.core.ui.R
import com.mobile.e2m.home.navigation.homeRootNavGraph

@Immutable
data class GetStrings(
    val homeTxt: String,
    val searchSongTxt: String,
    val recommendTxt: String,
    val recentlyPlayedTxt: String,
    val playedAllTxt: String,
) {
    companion object {
        @Composable
        internal fun default(
            context: Context = LocalContext.current,
        ) = GetStrings(
            homeTxt = context.getString(R.string.home),
            searchSongTxt = context.getString(R.string.searchSong),
            recommendTxt = context.getString(R.string.recommend),
            recentlyPlayedTxt = context.getString(R.string.recentlyPlayed),
            playedAllTxt = context.getString(R.string.playedAll),
        )
    }
}

@Composable
internal fun getString(): GetStrings {
    return GetStrings.default()
}

@Composable
fun HomeRootScreen(
    checkMiniPlayer: Boolean = false,
    navController: NavHostController = rememberNavController(),
    menuOnClick: () -> Unit = { },
    playSongOnClick: (SongsEntity) -> Unit = { },
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
        homeRootNavGraph(
            checkMiniPlayer = checkMiniPlayer,
            navController = navController,
            menuOnClick = { menuOnClick() },
            playSongOnClick = { playSongOnClick(it) },
        )
    }
}
