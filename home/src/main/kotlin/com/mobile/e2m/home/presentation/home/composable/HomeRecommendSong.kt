package com.mobile.e2m.home.presentation.home.composable

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.mobile.e2m.core.ui.theme.E2MTheme
import com.mobile.e2m.core.ui.composable.E2MAsyncImage
import com.mobile.e2m.core.ui.composable.shimmerEffect
import com.mobile.e2m.home.presentation.getString
import com.mobile.e2m.home.presentation.home.HomeConfig.NUMBER_LOADING

@Immutable
data class HomeRecommendSongData(
    val imageUrl: String?,
    val name: String?,
    val singer: String?,
)

@Composable
internal fun HomeRecommendSong(
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    items: List<HomeRecommendSongData>,
) {
    val size = E2MTheme.alias.size
    val color = E2MTheme.alias.color
    val style = E2MTheme.typography

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(size.spacing.small)
    ) {
        Text(
            modifier = Modifier.padding(horizontal = size.spacing.small),
            text = getString().recommendTxt,
            style = style.title.bold,
            color = color.text.white,
        )

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(size.spacing.small2x),
            contentPadding = PaddingValues(horizontal = size.spacing.small)
        ) {
            if (isLoading) {
                items(NUMBER_LOADING) {
                    HomeRecommendSongLoad()
                }
            } else {
                items(items.size) {
                    HomeRecommendSongItem(
                        item = items[it]
                    )
                }
            }
        }
    }
}

@Composable
private fun HomeRecommendSongItem(
    modifier: Modifier = Modifier,
    item: HomeRecommendSongData,
) {
    val size = E2MTheme.alias.size
    val color = E2MTheme.alias.color
    val style = E2MTheme.typography

    Column(
        modifier = modifier
    ) {
        item.imageUrl?.let {
            E2MAsyncImage(
                modifier = Modifier
                    .height(size.spacing.large5x)
                    .width(size.spacing.large6x)
                    .clip(RoundedCornerShape(size.radius.radius2))
                    .border(
                        width = size.stroke.thick,
                        color = color.border.blur2Light,
                        shape = RoundedCornerShape(size.radius.radius2),
                    ),
                imageUrl = it,
                contentDescription = item.name,
                contentScale = ContentScale.Crop,
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

        item.singer?.let {
            Text(
                modifier = Modifier
                    .width(size.spacing.large6x)
                    .padding(top = size.spacing.small4x),
                text = it,
                style = style.small.regular,
                color = color.text.white600,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@Composable
private fun HomeRecommendSongLoad(
    modifier: Modifier = Modifier,
) {
    val size = E2MTheme.alias.size

    Column(
        modifier = modifier,
    ) {
        Box(
            modifier = Modifier
                .height(size.spacing.large5x)
                .width(size.spacing.large6x)
                .clip(RoundedCornerShape(size.radius.radius2))
                .shimmerEffect(),
        )

        Box(
            modifier = Modifier
                .height(size.spacing.medium)
                .width(size.spacing.large6x)
                .padding(top = size.spacing.small2x)
                .clip(shape = RoundedCornerShape(size.radius.radius1))
                .shimmerEffect()
        )

        Box(
            modifier = Modifier
                .height(size.spacing.small)
                .width(size.spacing.large6x)
                .padding(top = size.spacing.small4x)
                .clip(shape = RoundedCornerShape(size.radius.radius1))
                .shimmerEffect()
        )
    }
}

@Preview
@Composable
fun HomeRecommendSongItemPreview() {
    HomeRecommendSongItem(
        item = HomeRecommendSongData(
            imageUrl = "",
            name = "Tên bài hát",
            singer = "Tên nghệ sĩ",
        ),
    )
}
