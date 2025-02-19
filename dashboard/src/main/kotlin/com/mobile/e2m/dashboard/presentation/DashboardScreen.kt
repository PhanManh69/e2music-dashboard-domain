@file:OptIn(ExperimentalMaterial3Api::class)

package com.mobile.e2m.dashboard.presentation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.mobile.e2m.core.ui.composable.E2MScaffold
import com.mobile.e2m.core.ui.composable.background.E2MBackgroundDark
import com.mobile.e2m.core.ui.composable.shadowCustom
import com.mobile.e2m.core.ui.navigation.route.AppNavigationRoute
import com.mobile.e2m.core.ui.theme.E2MTheme
import com.mobile.e2m.dashboard.presentation.composable.DashboardNavButton
import com.mobile.e2m.dashboard.router.DashboardRouter
import com.mobile.e2m.home.navigation.homeRootDestination
import com.mobile.e2m.menu.presentation.MenuSheet
import com.mobile.e2m.music.navigation.musicRootDestination
import com.mobile.e2m.playmusic.presentation.miniPlayer.MiniPlayerSheet
import com.mobile.e2m.playmusic.presentation.playmusic.PlaymusicSheet
import com.mobile.e2m.profile.navigation.profileRootDestination
import kotlinx.coroutines.launch
import org.koin.compose.koinInject

sealed class SheetType {
    data object MiniPlayer : SheetType()
    data object PlayMusic : SheetType()
}

@Composable
internal fun DashboardScreen(
    navController: NavHostController = rememberNavController(),
    dashboardRouter: DashboardRouter = koinInject(),
) {
    DashboardScaffold(
        navController = navController,
        onTheme = {
            dashboardRouter.onTheme()
        },
        onTimer = {
            dashboardRouter.onTimer()
        },
        onEqualizer = {
            dashboardRouter.onEqualizer()
        },
        onMemory = {
            dashboardRouter.onMemory()
        },
        onLocalFile = {
            dashboardRouter.onLocalFile()
        },
        onDriveMode = {
            dashboardRouter.onDriveMode()
        },
        onAudioQuality = {
            dashboardRouter.onAudioQuality()
        },
        onPrivacySocial = {
            dashboardRouter.onPrivacySocial()
        },
        signOutOnClick = {
            dashboardRouter.onDashboard()
        }
    )
}

@Composable
internal fun DashboardScaffold(
    navController: NavHostController,
    onTheme: () -> Unit = { },
    onTimer: () -> Unit = { },
    onEqualizer: () -> Unit = { },
    onMemory: () -> Unit = { },
    onLocalFile: () -> Unit = { },
    onDriveMode: () -> Unit = { },
    onAudioQuality: () -> Unit = { },
    onPrivacySocial: () -> Unit = { },
    signOutOnClick: () -> Unit = { },
) {
    val current = LocalDensity.current
    val color = E2MTheme.alias.color.surface
    val size = E2MTheme.alias.size
    val scope = rememberCoroutineScope()
    val buttonBarSize = remember { mutableStateOf(Size.Zero) }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    var currentSheet by remember { mutableStateOf<SheetType?>(null) }

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
                    DashboardNavButton(
                        modifier = Modifier.onGloballyPositioned {
                            buttonBarSize.value = it.size.toSize()
                        },
                        navController = navController
                    )
                }
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
                        menuOnClick = {
                            scope.launch {
                                drawerState.apply {
                                    if (isClosed) open() else close()
                                }
                            }
                        },
                        playSongOnClick = {
                            currentSheet = SheetType.PlayMusic
                        }
                    )
                    musicRootDestination(
                        menuOnClick = {
                            scope.launch {
                                drawerState.apply {
                                    if (isClosed) open() else close()
                                }
                            }
                        }
                    )
                    profileRootDestination(
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

            AnimatedVisibility(
                modifier = Modifier
                    .padding(bottom = with(current) { buttonBarSize.value.height.toDp() })
                    .align(Alignment.BottomCenter),
                visible = currentSheet != SheetType.PlayMusic,
                enter = slideInVertically { it },
                exit = slideOutVertically { it }
            ) {
                MiniPlayerSheet(
                    goToPlaymusic = {
                        scope.launch {
                            bottomSheetState.show()
                            currentSheet = SheetType.PlayMusic
                        }
                    }
                )
            }

            if (currentSheet == SheetType.PlayMusic) {
                ModalBottomSheet(
                    onDismissRequest = {
                        scope.launch {
                            bottomSheetState.hide()
                            currentSheet = SheetType.MiniPlayer
                        }
                    },
                    sheetState = bottomSheetState,
                    containerColor = Color.Transparent,
                    shape = RoundedCornerShape(size.radius.radius7),
                    contentWindowInsets = { WindowInsets(size.spacing.none) },
                    dragHandle = { },
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        E2MBackgroundDark()

                        PlaymusicSheet(
                            goBack = {
                                scope.launch {
                                    bottomSheetState.hide()
                                    currentSheet = SheetType.MiniPlayer
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}
