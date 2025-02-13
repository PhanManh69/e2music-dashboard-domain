package com.mobile.e2m.music.presentation.artists.composable

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import com.mobile.e2m.core.ui.R
import com.mobile.e2m.core.ui.composable.E2MAsyncImage
import com.mobile.e2m.core.ui.composable.E2MIcon
import com.mobile.e2m.core.ui.composable.shimmerEffect
import com.mobile.e2m.core.ui.theme.E2MTheme
import com.mobile.e2m.music.presentation.getString

@Composable
internal fun ArtistLoadItem(
    modifier: Modifier = Modifier,
) {
    val size = E2MTheme.alias.size

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.spacedBy(size.spacing.small2x)
    ) {
        Box(
            modifier = Modifier
                .size(size.icon.large1X)
                .clip(RoundedCornerShape(size.radius.radius2))
                .shimmerEffect(),
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(top = size.spacing.small2x),
            verticalArrangement = Arrangement.spacedBy(size.spacing.small2x),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(size.spacing.medium)
                    .clip(shape = RoundedCornerShape(size.radius.radius1))
                    .shimmerEffect()
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(size.spacing.small)
                    .clip(shape = RoundedCornerShape(size.radius.radius1))
                    .shimmerEffect()
            )
        }
    }
}
