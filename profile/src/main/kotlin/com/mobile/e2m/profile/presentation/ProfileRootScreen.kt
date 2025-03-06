package com.mobile.e2m.profile.presentation

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
import com.mobile.e2m.profile.navigation.profileRootNavGraph

@Immutable
data class GetStrings(
    val profileTxt: String,
    val followersTxt: String,
    val followingTxt: String,
    val likesTxt: String,
    val viewAllTxt: String,
    val viewsTxt: String,
    val myMusicTxt: String,
    val signOutTxt: String,
    val cancelTxt: String,
    val doYouWantToLogOutTxt: String,
) {
    companion object {
        @Composable
        internal fun default(
            context: Context = LocalContext.current,
        ) = GetStrings(
            profileTxt = context.getString(R.string.profile),
            followersTxt = context.getString(R.string.followers),
            followingTxt = context.getString(R.string.following),
            likesTxt = context.getString(R.string.likes),
            viewAllTxt = context.getString(R.string.viewAll),
            viewsTxt = context.getString(R.string.views),
            myMusicTxt = context.getString(R.string.myMusic),
            signOutTxt = context.getString(R.string.signOut),
            cancelTxt = context.getString(R.string.cancel),
            doYouWantToLogOutTxt = context.getString(R.string.doYouWantToLogOut),
        )
    }
}

@Composable
internal fun getString(): GetStrings {
    return GetStrings.default()
}

@Composable
fun ProfileRootScreen(
    navController: NavHostController = rememberNavController(),
    checkMiniPlayer: Boolean = false,
    menuOnClick: () -> Unit = { },
    signOutOnClick: () -> Unit = { },
) {
    NavHost(
        navController = navController,
        startDestination = AppNavigationRoute.Dashboard.Profile,
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
        profileRootNavGraph(
            checkMiniPlayer = checkMiniPlayer,
            navController = navController,
            menuOnClick = { menuOnClick() },
            signOutOnClick = { signOutOnClick() },
        )
    }
}
