package com.mobile.e2m.music.presentation.genres

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mobile.e2m.core.ui.R
import com.mobile.e2m.core.ui.theme.E2MTheme
import com.mobile.e2m.music.data.local.fakeGenresData
import com.mobile.e2m.music.presentation.genres.composable.GenreData
import com.mobile.e2m.music.presentation.genres.composable.GenreItem
import kotlinx.collections.immutable.persistentListOf

@Composable
internal fun GenresScreen() {
    GenresScaffold()
}

@Composable
private fun GenresScaffold(
    modifier: Modifier = Modifier,
) {
    val size = E2MTheme.alias.size

    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(count = 2),
        verticalArrangement = Arrangement.spacedBy(size.spacing.small),
        horizontalArrangement = Arrangement.spacedBy(size.spacing.small),
        contentPadding = PaddingValues(
            vertical = size.spacing.large,
            horizontal = size.spacing.small,
        )
    ) {

        items(fakeGenresData().size) {
            GenreItem(
                items = fakeGenresData()[it]
            )
        }
    }
}
