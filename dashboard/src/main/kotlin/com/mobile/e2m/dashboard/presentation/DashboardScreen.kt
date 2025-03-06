package com.mobile.e2m.dashboard.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.mobile.e2m.dashboard.presentation.composable.DashboardScaffold
import com.mobile.e2m.dashboard.router.DashboardRouter
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject

@Composable
internal fun DashboardScreen(
    navController: NavHostController = rememberNavController(),
    dashboardRouter: DashboardRouter = koinInject(),
    viewModel: DashboardViewModel = koinViewModel(),
) {
    val state by viewModel.stateFlow.collectAsStateWithLifecycle()

    DashboardScaffold(
        navController = navController,
        duration = state.maxDuration,
        currentPosition = state.currentDuration,
        isLoading = state.isLoading,
        isPlaying = state.isPlaying,
        currentSong = state.currentSong,
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
        },
        playPauseOnClick = {
            viewModel.trySendAction(DashboardAction.PlayPauseClick)
        },
        rewindOnClick = {
            viewModel.trySendAction(DashboardAction.PreviousClick)
        },
        forwardOnClick = {
            viewModel.trySendAction(DashboardAction.NextClick)
        },
        playingSongOnClick = {
            viewModel.trySendAction(DashboardAction.SongSelectedClick(it))
        },
    )
}
