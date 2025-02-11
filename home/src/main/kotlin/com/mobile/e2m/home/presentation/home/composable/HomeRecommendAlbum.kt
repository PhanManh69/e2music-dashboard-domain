package com.mobile.e2m.home.presentation.home.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.zIndex
import com.mobile.e2m.core.ui.theme.E2MTheme
import com.mobile.e2m.home.R

@Composable
internal fun HomeRecommendAlbum(
    modifier: Modifier = Modifier,
) {
    val size = E2MTheme.alias.size

    Row(
        modifier = modifier
            .padding(horizontal = size.spacing.small)
            .padding(top = size.spacing.large),
        horizontalArrangement = Arrangement.spacedBy(size.spacing.small2x)
    ) {
        repeat(2) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(size.spacing.small2x)
            ) {
                repeat(4) {
                    HomeRecommendAlbumItem(
                        imageId = R.drawable.img_song,
                        title = "Tên album",
                    )
                }
            }
        }
    }
}

@Composable
private fun HomeRecommendAlbumItem(
    modifier: Modifier = Modifier,
    imageId: Int? = null,
    title: String? = null,
) {
    val size = E2MTheme.alias.size
    val color = E2MTheme.alias.color
    val style = E2MTheme.typography

    Box(modifier = modifier) {
        Row(
            modifier = Modifier
                .border(
                    width = size.stroke.thick,
                    color = color.border.whiteDark,
                    shape = RoundedCornerShape(size.radius.radius2)
                ),
            horizontalArrangement = Arrangement.spacedBy(size.spacing.small4x),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(modifier = Modifier.size(size.spacing.large4x))

            title?.let {
                Text(
                    modifier = Modifier.weight(1f),
                    text = it,
                    style = style.small.semiBold,
                    color = color.text.white200,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }

        imageId?.let {
            Image(
                modifier = Modifier
                    .size(size.spacing.large4x)
                    .clip(RoundedCornerShape(size.radius.radius2))
                    .border(
                        width = size.stroke.thick,
                        color = color.border.blur2Light,
                        shape = RoundedCornerShape(size.radius.radius2),
                    ),
                painter = painterResource(id = imageId),
                contentDescription = title,
                contentScale = ContentScale.Crop,
            )
        }
    }
}

@Preview
@Composable
fun HomeRecommendAlbumPreview() {
    HomeRecommendAlbumItem(
        imageId = R.drawable.img_song,
        title = "Tên album",
    )
}
