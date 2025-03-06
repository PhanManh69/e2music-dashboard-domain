package com.mobile.e2m.home.presentation.home.composable

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
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.mobile.e2m.core.ui.theme.E2MTheme
import com.mobile.e2m.core.ui.composable.E2MAsyncImage

@Immutable
data class HomeRecommendAlbumData(
    val imageUrl: String?,
    val title: String?,
)

@Composable
internal fun HomeRecommendAlbum(
    modifier: Modifier = Modifier,
    items: List<HomeRecommendAlbumData>,
) {
    val size = E2MTheme.alias.size

    Row(
        modifier = modifier.padding(horizontal = size.spacing.small),
        horizontalArrangement = Arrangement.spacedBy(size.spacing.small2x)
    ) {
        val columns = items.chunked((items.size + 1) / 2)

        columns.forEach { columnItems ->
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(size.spacing.small2x)
            ) {
                columnItems.forEach { item ->
                    HomeRecommendAlbumItem(item = item)
                }
            }
        }
    }
}

@Composable
private fun HomeRecommendAlbumItem(
    modifier: Modifier = Modifier,
    item: HomeRecommendAlbumData,
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

            item.title?.let {
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

        item.imageUrl?.let {
            E2MAsyncImage(
                modifier = Modifier
                    .size(size.spacing.large4x)
                    .clip(RoundedCornerShape(size.radius.radius2))
                    .border(
                        width = size.stroke.thick,
                        color = color.border.blur2Light,
                        shape = RoundedCornerShape(size.radius.radius2),
                    ),
                imageUrl = it,
                contentDescription = item.title,
                contentScale = ContentScale.Crop,
            )
        }
    }
}

@Preview
@Composable
fun HomeRecommendAlbumPreview() {
    HomeRecommendAlbumItem(
        item = HomeRecommendAlbumData(
            imageUrl = "https://lh3.googleusercontent.com/9fE20eMGF4KeEmzNLgba9buDFfTIs68bj1l4U8F-jIKiSv-QoSOMlobhHfy0-puH8ly4-XyAyK1iI5s=w544-h544-l90-rj",
            title = "Tên album"
        ),
    )
}
