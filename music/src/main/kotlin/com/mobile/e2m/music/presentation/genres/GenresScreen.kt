package com.mobile.e2m.music.presentation.genres

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mobile.e2m.core.ui.theme.E2MTheme
import com.mobile.e2m.music.data.local.fakeGenresData
import com.mobile.e2m.music.presentation.genres.composable.GenreItem

@Composable
internal fun GenresScreen(
    checkMiniPlayer: Boolean = false,
) {
    GenresScaffold(
        checkMiniPlayer = checkMiniPlayer,
    )
}

@Composable
private fun GenresScaffold(
    modifier: Modifier = Modifier,
    checkMiniPlayer: Boolean = false,
) {
    val size = E2MTheme.alias.size

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

        items(fakeGenresData().size) {
            GenreItem(
                items = fakeGenresData()[it]
            )
        }
    }
}
