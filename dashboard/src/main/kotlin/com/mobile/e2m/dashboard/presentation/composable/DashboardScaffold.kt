package com.mobile.e2m.dashboard.presentation.composable

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DrawerValue
import com.mobile.e2m.core.ui.composable.scaffold.FabPosition
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.mobile.e2m.core.datasource.remote.firebase.data.entity.SongsEntity
import com.mobile.e2m.core.ui.composable.scaffold.E2MScaffold
import com.mobile.e2m.core.ui.composable.shadowCustom
import com.mobile.e2m.core.ui.navigation.route.AppNavigationRoute
import com.mobile.e2m.core.ui.theme.E2MTheme
import com.mobile.e2m.home.navigation.homeRootDestination
import com.mobile.e2m.menu.presentation.MenuSheet
import com.mobile.e2m.music.navigation.musicRootDestination
import com.mobile.e2m.profile.navigation.profileRootDestination
import kotlinx.coroutines.launch

@Composable
internal fun DashboardScaffold(
    duration: String? = null,
    currentPosition: String? = null,
    isLoading: Boolean = false,
    isPlaying: Boolean = true,
    navController: NavHostController,
    currentSong: SongsEntity? = null,
    onTheme: () -> Unit = { },
    onTimer: () -> Unit = { },
    onEqualizer: () -> Unit = { },
    onMemory: () -> Unit = { },
    onLocalFile: () -> Unit = { },
    onDriveMode: () -> Unit = { },
    onAudioQuality: () -> Unit = { },
    onPrivacySocial: () -> Unit = { },
    signOutOnClick: () -> Unit = { },
    rewindOnClick: () -> Unit = { },
    forwardOnClick: () -> Unit = { },
    playPauseOnClick: () -> Unit = { },
    playingSongOnClick: (SongsEntity) -> Unit = { },
) {
    val color = E2MTheme.alias.color.surface
    val size = E2MTheme.alias.size
    val scope = rememberCoroutineScope()
    val buttonBarSize = remember { mutableStateOf(Size.Zero) }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val checkShowPlaymusicState = remember { mutableStateOf(false) }

    var selectedSong by remember { mutableStateOf<SongsEntity?>(null) }
    var checkMiniPlayer by remember { mutableStateOf(false) }


    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerShape = RoundedCornerShape(size.radius.radius7),
                drawerContainerColor = color.shadowDark,
                windowInsets = WindowInsets(left = 0),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(end = size.spacing.large8x)
                    .shadowCustom(
                        color = color.shadowDark,
                        shapeRadius = size.radius.radius7,
                        blurRadius = size.spacing.small4x,
                    )
            ) {
                MenuSheet(
                    goToTheme = { onTheme() },
                    goToTimer = { onTimer() },
                    goToEqualizer = { onEqualizer() },
                    goToMemory = { onMemory() },
                    goToLocalFile = { onLocalFile() },
                    goToDriveMode = { onDriveMode() },
                    goToAudioQuality = { onAudioQuality() },
                    goToPrivacySocial = { onPrivacySocial() },
                )
            }
        }
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            E2MScaffold(
                bottomBar = {
                    if (!checkShowPlaymusicState.value) {
                        DashboardNavButton(
                            modifier = Modifier
                                .onGloballyPositioned {
                                    buttonBarSize.value = it.size.toSize()
                                }
                                .align(Alignment.BottomCenter),
                            navController = navController
                        )
                    }
                },
                floatingActionButton = {
                    currentSong?.run {
                        checkMiniPlayer = true

                        DashboardPlayingSong(
                            duration = duration,
                            currentPosition = currentPosition,
                            currentSong = currentSong,
                            isLoading = isLoading,
                            isPlaying = isPlaying,
                            checkShowPlaymusicState = checkShowPlaymusicState,
                            playPauseOnClick = { playPauseOnClick() },
                            rewindOnClick = { rewindOnClick() },
                            forwardOnClick = { forwardOnClick() },
                            playingSongOnClick = { song -> playingSongOnClick(song) },
                        )
                    } ?: run {
                        checkMiniPlayer = false

                        Spacer(modifier = Modifier.height(0.dp))
                    }
                },
                floatingActionButtonPosition = FabPosition.Bottom,
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
                    homeRootDestination(
                        checkMiniPlayer = checkMiniPlayer,
                        menuOnClick = {
                            scope.launch {
                                drawerState.apply {
                                    if (isClosed) open() else close()
                                }
                            }
                        },
                        playSongOnClick = {
                            playingSongOnClick(it)
                            selectedSong = it
                        }
                    )
                    musicRootDestination(
                        checkMiniPlayer = checkMiniPlayer,
                        menuOnClick = {
                            scope.launch {
                                drawerState.apply {
                                    if (isClosed) open() else close()
                                }
                            }
                        }
                    )
                    profileRootDestination(
                        checkMiniPlayer = checkMiniPlayer,
                        menuOnClick = {
                            scope.launch {
                                drawerState.apply {
                                    if (isClosed) open() else close()
                                }
                            }
                        },
                        signOutOnClick = { signOutOnClick() }
                    )
                }
            }
        }
    }
}
