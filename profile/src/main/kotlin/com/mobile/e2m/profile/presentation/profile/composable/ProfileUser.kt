package com.mobile.e2m.profile.presentation.profile.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import com.mobile.e2m.core.ui.R
import com.mobile.e2m.core.ui.composable.E2MAsyncImage
import com.mobile.e2m.core.ui.composable.E2MIcon
import com.mobile.e2m.core.ui.theme.E2MTheme
import com.mobile.e2m.profile.presentation.getString

@Immutable
data class ProfileUserData(
    val bannerId: Int?,
    val avatarId: Int?,
    val fullname: String?,
    val numberFollowers: Int?,
    val numberFollowing: Int?,
)

@Composable
internal fun ProfileBanner(
    modifier: Modifier = Modifier,
    items: ProfileUserData,
) {
    Box(
        modifier = modifier
    ) {
        E2MAsyncImage(
            modifier = Modifier.fillMaxSize(),
            imageId = items.bannerId,
            contentScale = ContentScale.Crop,
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = E2MTheme.alias.color.surface.black.copy(alpha = 0.4f))
        )
    }
}

@Composable
internal fun ProfileAvatar(
    modifier: Modifier = Modifier,
    items: ProfileUserData,
) {
    E2MAsyncImage(
        modifier = modifier
            .clip(CircleShape)
            .background(
                color = E2MTheme.alias.color.surface.black,
                shape = CircleShape,
            ),
        imageId = items.avatarId,
        contentScale = ContentScale.Crop,
    )
}

@Composable
internal fun ProfileName(
    modifier: Modifier = Modifier,
    items: ProfileUserData,
) {
    val size = E2MTheme.alias.size
    val color = E2MTheme.alias.color
    val style = E2MTheme.typography

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(size.spacing.small4x),
    ) {
        items.fullname?.let {
            Text(
                text = it,
                style = style.base.bold,
                color = color.text.white,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(size.spacing.small4x),
        ) {
            items.numberFollowers?.let {
                Text(
                    text = "$it ${getString().followersTxt}",
                    style = style.small.regular,
                    color = color.text.white600,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }

            E2MIcon(
                modifier = Modifier.size(size.icon.small4X),
                iconId = R.drawable.ic_dots,
                tint = color.text.white600,
            )

            items.numberFollowing?.let {
                Text(
                    text = "$it ${getString().followingTxt}",
                    style = style.small.regular,
                    color = color.text.white600,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}
