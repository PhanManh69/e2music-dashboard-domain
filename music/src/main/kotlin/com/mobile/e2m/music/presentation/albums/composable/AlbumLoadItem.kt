package com.mobile.e2m.music.presentation.albums.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.mobile.e2m.core.ui.composable.shimmerEffect
import com.mobile.e2m.core.ui.theme.E2MTheme

@Composable
internal fun AlbumLoadItem(
    modifier: Modifier = Modifier,
) {
    val size = E2MTheme.alias.size

    Column(
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .aspectRatio(ratio = 1f)
                .clip(RoundedCornerShape(size.radius.radius2))
                .shimmerEffect()
        )

        Spacer(modifier = Modifier.height(size.spacing.small2x))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(size.spacing.medium)
                .clip(RoundedCornerShape(size.radius.radius2))
                .shimmerEffect(),
        )
        Spacer(modifier = Modifier.height(size.spacing.small4x))


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(size.spacing.small)
                .clip(RoundedCornerShape(size.radius.radius2))
                .shimmerEffect(),
        )
    }
}
