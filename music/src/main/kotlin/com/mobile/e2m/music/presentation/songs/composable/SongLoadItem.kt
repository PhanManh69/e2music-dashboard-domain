package com.mobile.e2m.music.presentation.songs.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.mobile.e2m.core.ui.composable.shimmerEffect
import com.mobile.e2m.core.ui.theme.E2MTheme

@Composable
internal fun SongLoadItem(
    modifier: Modifier = Modifier,
) {
    val size = E2MTheme.alias.size

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(size.spacing.small2x),
    ) {
        Box(
            modifier = Modifier
                .size(size.icon.large)
                .clip(CircleShape)
                .shimmerEffect(),
        )

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(size.spacing.small4x),
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
