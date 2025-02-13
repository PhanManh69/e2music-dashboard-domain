package com.mobile.e2m.music.presentation.playlists.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import com.mobile.e2m.core.ui.R
import com.mobile.e2m.core.ui.composable.E2MAsyncImage
import com.mobile.e2m.core.ui.composable.E2MIcon
import com.mobile.e2m.core.ui.theme.E2MTheme
import com.mobile.e2m.music.presentation.getString

@Composable
internal fun PlaylistRecommend(
    modifier: Modifier = Modifier,
) {
    val size = E2MTheme.alias.size
    val color = E2MTheme.alias.color.border

    Row(
        modifier = modifier
            .padding(horizontal = size.spacing.small)
            .clip(RoundedCornerShape(size.radius.radius2))
            .border(
                width = size.stroke.thick,
                color = color.blur2Light,
                shape = RoundedCornerShape(size.radius.radius2)
            ),
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            PlaylistRecommendItem(
                modifier = Modifier.clip(RoundedCornerShape(topStart = size.radius.radius2)),
                imageId = R.drawable.img_playlist1,
                title = "Top 100 nhạc Việt",
                numberSongs = 100,
                shape = RoundedCornerShape(topStart = size.radius.radius2),
            )

            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                thickness = size.stroke.thick,
                color = color.blackLight
            )

            PlaylistRecommendItem(
                modifier = Modifier.clip(RoundedCornerShape(bottomStart = size.radius.radius2)),
                imageId = R.drawable.img_playlist2,
                title = "Top 100 nhạc Hàn",
                numberSongs = 100,
                shape = RoundedCornerShape(bottomStart = size.radius.radius2),
            )
        }

        VerticalDivider(
            modifier = Modifier.height(size.spacing.large7x * 2),
            thickness = size.stroke.thick,
            color = color.blackLight
        )

        Column(
            modifier = Modifier.weight(1f)
        ) {
            PlaylistRecommendItem(
                modifier = Modifier.clip(RoundedCornerShape(topEnd = size.radius.radius2)),
                imageId = R.drawable.img_playlist3,
                title = "Top 100 nhạc Âu Mỹ",
                numberSongs = 100,
                shape = RoundedCornerShape(topEnd = size.radius.radius2),
            )

            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                thickness = size.stroke.thick,
                color = color.blackLight
            )

            PlaylistRecommendItem(
                modifier = Modifier.clip(RoundedCornerShape(bottomEnd = size.radius.radius2)),
                imageId = R.drawable.img_playlist4,
                title = "Top 100 bài hát thịnh hành",
                numberSongs = 100,
                shape = RoundedCornerShape(bottomEnd = size.radius.radius2),
            )
        }
    }
}

@Composable
private fun PlaylistRecommendItem(
    modifier: Modifier = Modifier,
    imageId: Int? = null,
    title: String? = null,
    numberSongs: Int? = null,
    shape: Shape,
    onClick: () -> Unit = { },
) {
    val size = E2MTheme.alias.size
    val color = E2MTheme.alias.color
    val style = E2MTheme.typography

    Box(
        modifier = modifier
    ) {
        E2MAsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(size.spacing.large7x)
                .blur(
                    radius = size.stroke.thickX,
                    edgeTreatment = BlurredEdgeTreatment(shape)
                ),
            imageId = imageId,
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(size.spacing.large7x)
                .background(color = color.surface.black.copy(alpha = 0.4f)),
        )

        Row(
            modifier = Modifier
                .padding(size.spacing.small2x)
                .align(Alignment.BottomCenter),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.spacedBy(size.spacing.small4x),
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(size.spacing.small4x)
            ) {
                title?.let {
                    Text(
                        text = it,
                        style = style.small.regular,
                        color = color.text.white200,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                    )
                }

                numberSongs?.let {
                    Text(
                        text = "$it ${getString().songLowerTxt}",
                        style = style.caption.regular,
                        color = color.text.white600,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }

            E2MIcon(
                modifier = Modifier.size(size.icon.smallX),
                iconId = R.drawable.ic_play_circle,
                tint = color.icon.blue2Light,
                onClick = { onClick() }
            )
        }
    }
}
