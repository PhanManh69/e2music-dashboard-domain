package com.mobile.e2m.home.presentation.home.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.mobile.e2m.core.datasource.remote.firebase.data.entity.SongsEntity
import com.mobile.e2m.core.ui.R
import com.mobile.e2m.core.ui.composable.E2MSongsData
import com.mobile.e2m.core.ui.composable.E2MSongsItem
import com.mobile.e2m.core.ui.composable.debounceClickable
import com.mobile.e2m.core.ui.composable.shimmerEffect
import com.mobile.e2m.core.ui.theme.E2MTheme

@Composable
internal fun HomeRecentlySongsItem(
    modifier: Modifier = Modifier,
    songIndex: Int,
    isCurrentPlaying: Boolean,
    songsList: List<SongsEntity>,
    playSongOnClick: (SongsEntity) -> Unit = { },
    favouriteSongOnClick: () -> Unit = { },
) {
    val size = E2MTheme.alias.size

    E2MSongsItem(
        modifier = modifier
            .padding(horizontal = size.spacing.small)
            .padding(bottom = if (songIndex < songsList.size - 1) size.spacing.small2x else size.spacing.none)
            .debounceClickable {
                playSongOnClick(songsList[songIndex])
            },
        iconId = R.drawable.ic_favourite,
        shape = RoundedCornerShape(size.radius.radius2),
        blur = if (isCurrentPlaying) size.spacing.none else size.stroke.thickX,
        songItem = songsList.map { item ->
            E2MSongsData(
                imageUrl = item.imageUrl,
                name = item.name,
                singer = item.singer,
            )
        }[songIndex],
        imageOnClick = {
            playSongOnClick(songsList[songIndex])
        },
        iconOnClick = { favouriteSongOnClick() },
    )
}

@Composable
internal fun HomeRecentlySongsLoad(
    modifier: Modifier = Modifier,
    loadIndex: Int,
    numberLoad: Int,
) {
    val size = E2MTheme.alias.size

    Row(
        modifier = modifier
            .padding(horizontal = size.spacing.small)
            .padding(bottom = if (loadIndex < numberLoad - 1) size.spacing.small2x else size.spacing.none),
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
