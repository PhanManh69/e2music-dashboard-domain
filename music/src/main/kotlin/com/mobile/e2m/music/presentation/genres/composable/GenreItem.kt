package com.mobile.e2m.music.presentation.genres.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.mobile.e2m.core.ui.composable.E2MAsyncImage
import com.mobile.e2m.core.ui.theme.E2MTheme
import com.mobile.e2m.music.presentation.getString

@Immutable
data class GenreData(
    val imageId: Int?,
    val title: Int?,
    val numberSongs: Int?,
)

@Composable
internal fun GenreItem(
    modifier: Modifier = Modifier,
    items: GenreData,
) {
    val context = LocalContext.current
    val size = E2MTheme.alias.size
    val color = E2MTheme.alias.color
    val style = E2MTheme.typography

    Box(
        modifier = modifier.clip(RoundedCornerShape(size.radius.radius2)),
        contentAlignment = Alignment.Center,
    ) {
        E2MAsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(size.spacing.large7x)
                .clip(RoundedCornerShape(size.radius.radius2))
                .blur(
                    radius = size.stroke.thickX,
                    edgeTreatment = BlurredEdgeTreatment(RoundedCornerShape(size.radius.radius2))
                ),
            imageId = items.imageId,
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(size.spacing.large7x)
                .background(color = color.surface.black.copy(alpha = 0.4f))
                .clip(RoundedCornerShape(size.radius.radius2))
                .border(
                    width = size.stroke.thick,
                    color = color.border.blur2Light,
                    shape = RoundedCornerShape(size.radius.radius2),
                ),
        )

        Column(
            modifier = Modifier.padding(horizontal = size.spacing.small2x),
            verticalArrangement = Arrangement.spacedBy(size.spacing.small4x),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            items.title?.let {
                Text(
                    text = context.getString(it),
                    style = style.base.semiBold,
                    color = color.text.white200,
                    textAlign = TextAlign.Center,
                )
            }

            items.numberSongs?.let {
                Text(
                    text = "$it ${getString().songLowerTxt}",
                    style = style.small.regular,
                    color = color.text.white600,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}
