package com.mobile.e2m.music.presentation.playlists.composable

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import com.mobile.e2m.core.ui.R
import com.mobile.e2m.core.ui.composable.E2MAsyncImage
import com.mobile.e2m.core.ui.theme.E2MTheme
import com.mobile.e2m.music.presentation.getString

@Composable
internal fun PlaylistYour(
    modifier: Modifier = Modifier,
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
            items(8) {
                PlaylistYourItem(
                    imageId = R.drawable.img_song,
                    title = "Tên danh sách phát của bạn",
                )
            }
        }
    }
}

@Composable
private fun PlaylistYourItem(
    modifier: Modifier = Modifier,
    imageId: Int? = null,
    title: String? = null,
) {
    val size = E2MTheme.alias.size
    val color = E2MTheme.alias.color
    val style = E2MTheme.typography

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(size.spacing.small2x),
    ) {
        E2MAsyncImage(
            modifier = Modifier
                .width(size.spacing.large6x)
                .height(size.spacing.large5x)
                .clip(RoundedCornerShape(size.radius.radius2))
                .border(
                    width = size.stroke.thick,
                    color = color.border.blur2Light,
                    shape = RoundedCornerShape(size.radius.radius2)
                ),
            imageId = imageId,
            contentScale = ContentScale.Crop,
        )

        title?.let {
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
