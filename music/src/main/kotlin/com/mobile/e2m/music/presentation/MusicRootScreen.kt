package com.mobile.e2m.music.presentation

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
import com.mobile.e2m.core.ui.R
import com.mobile.e2m.core.ui.navigation.route.AppNavigationRoute
import com.mobile.e2m.music.navigation.musicRootNavGraph

@Immutable
data class GetStrings(
    val musicTxt: String,
    val albumTxt: String,
    val songTxt: String,
    val songLowerTxt: String,
    val playlistYourTxt: String,
    val viewAllTxt: String,
) {
    companion object {
        @Composable
        internal fun default(
            context: Context = LocalContext.current,
        ) = GetStrings(
            musicTxt = context.getString(R.string.music),
            albumTxt = context.getString(R.string.album),
            songTxt = context.getString(R.string.song),
            songLowerTxt = context.getString(R.string.songLower),
            playlistYourTxt = context.getString(R.string.playlistYour),
            viewAllTxt = context.getString(R.string.viewAll),
        )
    }
}

@Composable
internal fun getString(): GetStrings {
    return GetStrings.default()
}

@Composable
fun MusicRootScreen(
    navController: NavHostController = rememberNavController(),
    menuOnClick: () -> Unit = { },
) {
    NavHost(
        navController = navController,
        startDestination = AppNavigationRoute.Dashboard.Music,
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
        musicRootNavGraph(
            navController = navController,
            menuOnClick = { menuOnClick() }
        )
    }
}
