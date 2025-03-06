package com.mobile.e2m.music.presentation.albums

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.mobile.e2m.core.ui.R
import com.mobile.e2m.core.ui.theme.E2MTheme
import com.mobile.e2m.music.presentation.albums.composable.AlbumItem
import com.mobile.e2m.music.presentation.albums.composable.AlbumLoadItem
import com.mobile.e2m.music.presentation.albums.composable.AlbumsData
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.plus
import kotlinx.coroutines.delay

@Composable
internal fun AlbumsScreen(
    checkMiniPlayer: Boolean = false,
) {
    AlbumsScaffold(
        checkMiniPlayer = checkMiniPlayer,
    )
}

@Composable
private fun AlbumsScaffold(
    modifier: Modifier = Modifier,
    checkMiniPlayer: Boolean = false,
) {
    val size = E2MTheme.alias.size
    val itemsList = persistentListOf<AlbumsData>().plus(
        List(100) {
            AlbumsData(
                imageId = R.drawable.img_song,
                title = "Tên Album $it",
                artist = "Tên nghệ sĩ $it",
                numberSongs = 10,
            )
        }
    )

    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        delay(2000L)
        isLoading = false
    }

    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(count = 2),
        verticalArrangement = Arrangement.spacedBy(size.spacing.small),
        horizontalArrangement = Arrangement.spacedBy(size.spacing.small),
        contentPadding = PaddingValues(
            top = size.spacing.large,
            bottom = if (checkMiniPlayer) size.spacing.large9x else size.spacing.large,
            start = size.spacing.small,
            end = size.spacing.small,
        )
    ) {
        if (isLoading) {
            items(count = 6) {
                AlbumLoadItem()
            }
        } else {
            items(itemsList.size) {
                AlbumItem(
                    items = itemsList[it]
                )
            }
        }
    }
}
