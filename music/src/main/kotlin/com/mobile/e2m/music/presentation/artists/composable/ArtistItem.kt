package com.mobile.e2m.music.presentation.artists.composable

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
data class ArtistData(
    val imageId: Int?,
    val name: String?,
    val numberAlbums: Int?,
    val numberSongs: Int?,
)

@Composable
internal fun ArtistItem(
    modifier: Modifier = Modifier,
    items: ArtistData,
) {
    val size = E2MTheme.alias.size
    val color = E2MTheme.alias.color
    val style = E2MTheme.typography

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.spacedBy(size.spacing.small2x)
    ) {
        E2MAsyncImage(
            modifier = Modifier
                .size(size.icon.large1X)
                .clip(RoundedCornerShape(size.radius.radius2))
                .border(
                    width = size.stroke.thick,
                    color = color.border.blur2Light,
                    shape = RoundedCornerShape(size.radius.radius2)
                ),
            imageId = items.imageId,
            contentScale = ContentScale.Crop,
        )

        Column (
            modifier = Modifier
                .weight(1f)
                .padding(top = size.spacing.small2x),
            verticalArrangement = Arrangement.spacedBy(size.spacing.small2x)
        ) {
            items.name?.let {
                Text(
                    text = it,
                    style = style.base.semiBold,
                    color = color.text.white200,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(size.spacing.small4x),
            ) {
                items.numberAlbums?.let {
                    Text(
                        text = "$it ${getString().albumTxt}",
                        style = style.small.regular,
                        color = color.text.white600,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }

                E2MIcon(
                    modifier = Modifier.size(size.icon.small4X),
                    iconId = R.drawable.ic_dots,
                    tint = color.text.white600,
                )

                items.numberSongs?.let {
                    Text(
                        text = "$it ${getString().songTxt}",
                        style = style.small.regular,
                        color = color.text.white600,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }
        }

        E2MIcon(
            modifier = Modifier
                .size(size.icon.smallX)
                .padding(top = size.spacing.small2x),
            iconId = R.drawable.ic_menu_dots_vertical,
            tint = color.icon.blue2Light,
        )
    }
}
