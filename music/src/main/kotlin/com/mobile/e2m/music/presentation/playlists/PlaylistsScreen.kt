package com.mobile.e2m.music.presentation.playlists

import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.IntOffset
import com.mobile.e2m.core.ui.composable.E2MIcon
import com.mobile.e2m.core.ui.theme.E2MTheme
import com.mobile.e2m.core.ui.R
import com.mobile.e2m.music.presentation.playlists.composable.PlaylistRecommend
import com.mobile.e2m.music.presentation.playlists.composable.PlaylistYour
import com.mobile.e2m.music.presentation.playlists.composable.PlaylistYourData
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.plus

@Composable
internal fun PlaylistsScreen(
    checkMiniPlayer: Boolean = false,
) {
    PlaylistScaffold(
        checkMiniPlayer = checkMiniPlayer,
    )
}

@Composable
private fun PlaylistScaffold(
    modifier: Modifier = Modifier,
    checkMiniPlayer: Boolean = false,
    addPlaylistOnClick: () -> Unit = { },
) {
    val size = E2MTheme.alias.size
    val color = E2MTheme.alias.color
    val current = LocalContext.current
    val boxWidth = remember { mutableIntStateOf(0) }
    val boxHeight = remember { mutableIntStateOf(0) }
    val offsetX = remember { mutableFloatStateOf(0f) }
    val offsetY = remember { mutableFloatStateOf(0f) }

    val itemsList = persistentListOf<PlaylistYourData>().plus(
        List(5) {
            PlaylistYourData(
                imageId = R.drawable.img_song,
                title = "Tên danh sách phát của bạn",
                numberSongs = 24,
            )
        }
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(bottom = if (checkMiniPlayer) size.spacing.large8x else size.spacing.none)
            .onGloballyPositioned { layoutCoordinates ->
                boxWidth.intValue = layoutCoordinates.size.width
                boxHeight.intValue = layoutCoordinates.size.height
                offsetX.floatValue =
                    boxWidth.intValue - (size.spacing.large4x.value * current.resources.displayMetrics.density)
                offsetY.floatValue =
                    boxHeight.intValue - (size.spacing.large4x.value * current.resources.displayMetrics.density)
            }
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(size.spacing.large),
            contentPadding = PaddingValues(vertical = size.spacing.large),
        ) {
            item {
                PlaylistRecommend()
            }

            item {
                HorizontalDivider(
                    modifier = Modifier.fillMaxWidth(),
                    thickness = size.stroke.thick,
                    color = color.border.blackLight,
                )
            }

            item {
                PlaylistYour(
                    items = itemsList,
                )
            }
        }

        SmallFloatingActionButton(
            modifier = Modifier
                .size(size.icon.large)
                .offset {
                    IntOffset(offsetX.floatValue.toInt(), offsetY.floatValue.toInt())
                }
                .pointerInput(Unit) {
                    detectDragGestures { change, dragAmount ->
                        change.consume()
                        offsetX.floatValue = (offsetX.floatValue + dragAmount.x)
                            .coerceIn(0f, (boxWidth.intValue - size.icon.large.toPx()))
                        offsetY.floatValue = (offsetY.floatValue + dragAmount.y)
                            .coerceIn(0f, (boxHeight.intValue - size.icon.large.toPx()))
                    }
                },
            shape = CircleShape,
            containerColor = color.surface.whiteButtonFocus,
            onClick = { addPlaylistOnClick() }
        ) {
            E2MIcon(
                modifier = Modifier.size(size.icon.small1X),
                iconId = R.drawable.ic_plus,
                tint = color.icon.blue2Light,
            )
        }
    }
}
