@file:OptIn(ExperimentalMaterial3Api::class)

package com.mobile.e2m.dashboard.presentation.composable

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.toSize
import com.mobile.e2m.core.datasource.remote.firebase.data.entity.SongsEntity
import com.mobile.e2m.core.ui.composable.background.E2MBackgroundDark
import com.mobile.e2m.core.ui.theme.E2MTheme
import com.mobile.e2m.playmusic.presentation.miniPlayer.MiniPlayerSheet
import com.mobile.e2m.playmusic.presentation.playmusic.PlaymusicSheet
import kotlinx.coroutines.launch

@Composable
internal fun DashboardPlayingSong(
    modifier: Modifier = Modifier,
    duration: String? = null,
    currentPosition: String? = null,
    isLoading: Boolean = false,
    isPlaying: Boolean = true,
    currentSong: SongsEntity,
    checkShowPlaymusicState: MutableState<Boolean>,
    rewindOnClick: () -> Unit = { },
    forwardOnClick: () -> Unit = { },
    playPauseOnClick: () -> Unit = { },
    playingSongOnClick: (SongsEntity) -> Unit = { },
) {
    val size = E2MTheme.alias.size.spacing
    val scope = rememberCoroutineScope()
    val headerCollapsedSize = remember { mutableStateOf(Size.Zero) }
    val sheetState = rememberStandardBottomSheetState(
        initialValue = SheetValue.PartiallyExpanded,
        skipHiddenState = true
    )
    val showMiniPlayer by animateFloatAsState(
        targetValue = if (sheetState.currentValue == SheetValue.Expanded) 0f else 1f,
        animationSpec = tween(durationMillis = 300), label = ""
    )

    BottomSheetScaffold(
        modifier = modifier,
        scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = sheetState),
        sheetPeekHeight = size.none,
        sheetContainerColor = Color.Transparent,
        sheetShadowElevation = size.none,
        sheetDragHandle = if (sheetState.currentValue == SheetValue.Expanded) {
            {
                checkShowPlaymusicState.value = true

                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    E2MBackgroundDark()

                    PlaymusicSheet(
                        duration = duration,
                        currentPosition = currentPosition,
                        isPlaying = isPlaying,
                        currentSong = currentSong,
                        goBack = {
                            scope.launch {
                                sheetState.partialExpand()
                            }
                        },
                        rewindOnClick = { rewindOnClick() },
                        forwardOnClick = { forwardOnClick() },
                        playPauseOnClick = { playPauseOnClick() },
                        playingSongOnClick = { playingSongOnClick(it) },
                    )
                }
            }
        } else {
            checkShowPlaymusicState.value = false
            null
        },
        sheetContent = {
            MiniPlayerSheet(
                modifier = Modifier
                    .scale(showMiniPlayer)
                    .onGloballyPositioned {
                        headerCollapsedSize.value = it.size.toSize()
                    },
                currentSong = currentSong,
                isLoading = isLoading,
                isPlaying = isPlaying,
                goToPlaymusic = {
                    scope.launch {
                        if (sheetState.currentValue == SheetValue.PartiallyExpanded) {
                            sheetState.expand()
                        } else {
                            sheetState.partialExpand()
                        }
                    }
                },
                rewindOnClick = { rewindOnClick() },
                forwardOnClick = { forwardOnClick() },
                playPauseOnClick = { playPauseOnClick() },
            )
        },
        content = { }
    )
}
