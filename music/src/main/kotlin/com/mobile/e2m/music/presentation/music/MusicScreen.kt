package com.mobile.e2m.music.presentation.music

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.mobile.e2m.core.ui.composable.E2MHeader
import com.mobile.e2m.core.ui.composable.E2MScaffold
import com.mobile.e2m.core.ui.composable.background.E2MBackgroundDark
import com.mobile.e2m.core.ui.R
import com.mobile.e2m.core.ui.navigation.route.AppNavigationRoute
import com.mobile.e2m.core.ui.theme.E2MTheme
import com.mobile.e2m.music.presentation.albums.albumsDestination
import com.mobile.e2m.music.presentation.artists.artistsDestination
import com.mobile.e2m.music.presentation.genres.genresDestination
import com.mobile.e2m.music.presentation.getString
import com.mobile.e2m.music.presentation.music.composable.MusicMenuTab
import com.mobile.e2m.music.presentation.playlists.playlistsDestination
import com.mobile.e2m.music.presentation.songs.songsDestination

@Composable
internal fun MusicScreen(
    navController: NavHostController = rememberNavController(),
    menuOnClick: () -> Unit = { },
) {
    MusicScaffold(
        navController = navController,
        leadingIconOnClick = { menuOnClick() }
    )
}

@Composable
private fun MusicScaffold(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    leadingIconOnClick: () -> Unit = { },
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        E2MBackgroundDark()

        E2MScaffold(
            topBar = {
                E2MHeader(
                    title = getString().musicTxt,
                    leadingIconId = R.drawable.ic_menu,
                    trailingIconId = R.drawable.ic_search,
                    leadingIconOnClick = { leadingIconOnClick() }
                )
            },
            content = {
                MusicContent(
                    navController = navController,
                )
            }
        )
    }
}

@Composable
private fun MusicContent(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    val size = E2MTheme.alias.size

    Column(
        modifier = modifier.fillMaxSize(),
    ) {
        MusicMenuTab(
            modifier = Modifier.padding(
                top = size.spacing.large,
                bottom = size.spacing.small4x
            ),
            navController = navController,
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(bottom = size.spacing.large8x),         //TODO: has mini player
        ) {
            NavHost(
                navController = navController,
                startDestination = AppNavigationRoute.Dashboard.Music.Song,
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
                songsDestination()
                playlistsDestination()
                albumsDestination()
                artistsDestination()
                genresDestination()
            }
        }
    }
}
