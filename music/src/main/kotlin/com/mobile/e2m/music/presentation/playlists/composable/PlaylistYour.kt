package com.mobile.e2m.music.presentation.playlists.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import com.mobile.e2m.core.ui.composable.E2MPlaylistItem
import com.mobile.e2m.core.ui.theme.E2MTheme
import com.mobile.e2m.music.presentation.getString

@Immutable
data class PlaylistYourData(
    val imageId: Int?,
    val title: String?,
    val numberSongs: Int?,
)

@Composable
internal fun PlaylistYour(
    modifier: Modifier = Modifier,
    items: List<PlaylistYourData>,
) {
    val size = E2MTheme.alias.size
    val color = E2MTheme.alias.color
    val style = E2MTheme.typography

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(size.spacing.small)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = size.spacing.small),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = getString().playlistYourTxt,
                style = style.title.bold,
                color = color.text.white,
            )

            Text(
                text = getString().viewAllTxt,
                style = style.base.bold,
                color = color.text.blur2Light,
            )
        }

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(size.spacing.small2x),
            contentPadding = PaddingValues(horizontal = size.spacing.small)
        ) {
            items(items.size) {
                PlaylistYourItem(
                    item = items[it],
                )
            }
        }
    }
}

@Composable
private fun PlaylistYourItem(
    modifier: Modifier = Modifier,
    item: PlaylistYourData,
) {
    val size = E2MTheme.alias.size
    val color = E2MTheme.alias.color
    val style = E2MTheme.typography

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(size.spacing.small2x),
    ) {
        E2MPlaylistItem(
            imageId = item.imageId,
            name = item.title,
            numberSongs = item.numberSongs,
        )

        item.title?.let {
            Text(
                modifier = Modifier.width(size.spacing.large6x),
                text = it,
                style = style.base.regular,
                color = color.text.white200,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}
