package com.mobile.e2m.dashboard.presentation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
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
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.mobile.e2m.core.ui.composable.E2MScaffold
import com.mobile.e2m.core.ui.composable.shadowCustom
import com.mobile.e2m.core.ui.navigation.route.AppNavigationRoute
import com.mobile.e2m.core.ui.theme.E2MTheme
import com.mobile.e2m.dashboard.presentation.composable.DashboardNavButton
import com.mobile.e2m.dashboard.router.DashboardRouter
import com.mobile.e2m.home.navigation.homeRootDestination
import com.mobile.e2m.menu.presentation.MenuSheet
import com.mobile.e2m.music.navigation.musicRootDestination
import com.mobile.e2m.profile.navigation.profileRootDestination
import kotlinx.coroutines.launch
import org.koin.compose.koinInject

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
) {
    val color = E2MTheme.alias.color.surface
    val size = E2MTheme.alias.size
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

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
        E2MScaffold(
            bottomBar = {
                DashboardNavButton(navController = navController)
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
                    }
                )
            }
        }
    }
}
