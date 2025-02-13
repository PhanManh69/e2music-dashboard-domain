package com.mobile.e2m.music.presentation.albums.composable

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import com.mobile.e2m.core.ui.composable.E2MAsyncImage
import com.mobile.e2m.core.ui.composable.E2MIcon
import com.mobile.e2m.core.ui.theme.E2MTheme
import com.mobile.e2m.core.ui.R
import com.mobile.e2m.music.presentation.getString

@Immutable
data class AlbumsData(
    val imageId: Int?,
    val title: String?,
    val artist: String?,
    val numberSongs: Int?,
)

@Composable
internal fun AlbumItem(
    modifier: Modifier = Modifier,
    items: AlbumsData,
    menuDotsOnClick: () -> Unit = { },
) {
    val size = E2MTheme.alias.size
    val color = E2MTheme.alias.color
    val style = E2MTheme.typography

    Column(
        modifier = modifier
    ) {
        E2MAsyncImage(
            modifier = Modifier
                .aspectRatio(ratio = 1f)
                .clip(RoundedCornerShape(size.radius.radius2))
                .border(
                    width = size.stroke.thick,
                    color = color.border.blur2Light,
                    shape = RoundedCornerShape(size.radius.radius2)
                ),
            imageId = items.imageId,
            contentScale = ContentScale.Crop,
        )

        Row(
            modifier = Modifier.padding(top = size.spacing.small2x, bottom = size.spacing.small4x),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(size.spacing.small4x)
        ) {
            items.title?.let {
                Text(
                    modifier = Modifier.weight(1f),
                    text = it,
                    style = style.small.regular,
                    color = color.text.white200,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }

            E2MIcon(
                modifier = Modifier.size(size.icon.small2X),
                iconId = R.drawable.ic_menu_dots_vertical,
                tint = color.icon.blue2Light,
                onClick = { menuDotsOnClick() }
            )
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(size.spacing.small4x)
        ) {
            items.artist?.let {
                Text(
                    modifier = Modifier.weight(1f),
                    text = it,
                    style = style.caption.regular,
                    color = color.text.white600,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }

            items.numberSongs?.let {
                Text(
                    text = "$it ${getString().songLowerTxt}",
                    style = style.caption.regular,
                    color = color.text.white600,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}
