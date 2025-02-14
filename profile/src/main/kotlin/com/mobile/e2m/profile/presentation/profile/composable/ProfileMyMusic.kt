package com.mobile.e2m.profile.presentation.profile.composable

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import com.mobile.e2m.core.ui.composable.E2MImage
import com.mobile.e2m.core.ui.composable.E2MPlaylistItem
import com.mobile.e2m.core.ui.theme.E2MTheme
import com.mobile.e2m.profile.presentation.getString

@Immutable
data class ProfileMyMusicData(
    val imageId: Int?,
    val isAlbum: Boolean,
    val name: String?,
    val view: Int?,
    val numberSongs: Int?,
)

@Composable
internal fun ProfileMyMusic(
    modifier: Modifier = Modifier,
    items: List<ProfileMyMusicData>,
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
                text = getString().myMusicTxt,
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
                ProfileMyMusic(
                    item = items[it]
                )
            }
        }
    }
}

@Composable
private fun ProfileMyMusic(
    modifier: Modifier = Modifier,
    item: ProfileMyMusicData,
) {
    val size = E2MTheme.alias.size
    val color = E2MTheme.alias.color
    val style = E2MTheme.typography

    Column(
        modifier = modifier,
    ) {
        if (!item.isAlbum) {
            item.imageId?.let {
                E2MImage(
                    modifier = Modifier
                        .height(size.spacing.large5x)
                        .width(size.spacing.large6x)
                        .clip(RoundedCornerShape(size.radius.radius2))
                        .border(
                            width = size.stroke.thick,
                            color = color.border.blur2Light,
                            shape = RoundedCornerShape(size.radius.radius2),
                        ),
                    imageId = it,
                    contentDescription = item.name,
                )
            }
        } else {
            E2MPlaylistItem(
                imageId = item.imageId,
                name = item.name,
                numberSongs = item.numberSongs,
            )
        }

        item.name?.let {
            Text(
                modifier = Modifier
                    .width(size.spacing.large6x)
                    .padding(top = size.spacing.small2x),
                text = it,
                style = style.base.regular,
                color = color.text.white200,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }

        item.view?.let {
            Text(
                modifier = Modifier
                    .width(size.spacing.large6x)
                    .padding(top = size.spacing.small4x),
                text = "$it ${getString().viewsTxt}",
                style = style.small.regular,
                color = color.text.white600,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}
