package com.mobile.e2m.home.presentation.home.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.mobile.e2m.core.ui.theme.E2MTheme
import com.mobile.e2m.home.R
import com.mobile.e2m.home.presentation.getString

@Composable
internal fun HomeRecentlyPlayedSong(
    modifier: Modifier = Modifier,
) {
    val size = E2MTheme.alias.size
    val color = E2MTheme.alias.color
    val style = E2MTheme.typography

    Column(
        modifier = modifier
            .padding(horizontal = size.spacing.small)
            .padding(bottom = size.spacing.large),
        verticalArrangement = Arrangement.spacedBy(size.spacing.small)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = getString().recentlyPlayedTxt,
                style = style.title.bold,
                color = color.text.white,
            )

            Text(
                text = getString().playedAllTxt,
                style = style.base.bold,
                color = color.text.blur2Light,
            )
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(size.spacing.small2x),
        ) {
            repeat(8) {
                HomeRecentlyPlayedSongItem(
                    imageId = R.drawable.img_song,
                    name = "Tên bài hát",
                    singer = "Tên nghệ sĩ",
                )
            }
        }
    }
}

@Composable
private fun HomeRecentlyPlayedSongItem(
    modifier: Modifier = Modifier,
    imageId: Int? = null,
    name: String? = null,
    singer: String? = null,
) {
    val size = E2MTheme.alias.size
    val color = E2MTheme.alias.color
    val style = E2MTheme.typography

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(size.spacing.small2x),
    ) {
        Box(
            modifier = modifier,
            contentAlignment = Alignment.Center,
        ) {
            imageId?.let {
                Image(
                    modifier = Modifier
                        .size(size.spacing.large3X)
                        .clip(RoundedCornerShape(size.radius.radius2))
                        .border(
                            width = size.stroke.thick,
                            color = color.border.blur2Light,
                            shape = RoundedCornerShape(size.radius.radius2),
                        )
                        .blur(
                            radius = size.stroke.thickX,
                            edgeTreatment = BlurredEdgeTreatment(RoundedCornerShape(size.radius.radius2))
                        ),
                    painter = painterResource(id = imageId),
                    contentDescription = name,
                    contentScale = ContentScale.Crop,
                )
            }

            Icon(
                modifier = Modifier.size(size.spacing.large),
                painter = painterResource(id = R.drawable.ic_play_circle),
                contentDescription = null,
                tint = color.surface.blur2Light,
            )
        }

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(size.spacing.small4x)
        ) {
            name?.let {
                Text(
                    text = it,
                    style = style.base.regular,
                    color = color.text.white200,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }

            singer?.let {
                Text(
                    text = it,
                    style = style.small.regular,
                    color = color.text.white600,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }

        Icon(
            modifier = Modifier.size(size.spacing.large),
            painter = painterResource(id = R.drawable.ic_favourite),
            contentDescription = null,
            tint = color.surface.blur2Light,
        )
    }
}

@Preview
@Composable
fun HomeRecentlyPlayedSongItemPreview() {
    HomeRecentlyPlayedSongItem(
        imageId = R.drawable.img_song,
        name = "Tên bài hát",
        singer = "Tên nghệ sĩ",
    )
}
