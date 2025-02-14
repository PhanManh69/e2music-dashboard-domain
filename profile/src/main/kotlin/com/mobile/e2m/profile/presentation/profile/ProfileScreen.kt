package com.mobile.e2m.profile.presentation.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.mobile.e2m.core.ui.R
import com.mobile.e2m.core.ui.composable.E2MHeader
import com.mobile.e2m.core.ui.composable.E2MScaffold
import com.mobile.e2m.core.ui.composable.E2MSongsData
import com.mobile.e2m.core.ui.composable.background.E2MBackgroundDark
import com.mobile.e2m.core.ui.theme.E2MTheme
import com.mobile.e2m.profile.data.local.fakeProfileButtonData
import com.mobile.e2m.profile.presentation.getString
import com.mobile.e2m.profile.presentation.profile.composable.ProfileAvatar
import com.mobile.e2m.profile.presentation.profile.composable.ProfileBanner
import com.mobile.e2m.profile.presentation.profile.composable.ProfileButton
import com.mobile.e2m.profile.presentation.profile.composable.ProfileLikes
import com.mobile.e2m.profile.presentation.profile.composable.ProfileMyMusic
import com.mobile.e2m.profile.presentation.profile.composable.ProfileMyMusicData
import com.mobile.e2m.profile.presentation.profile.composable.ProfileUserData
import com.mobile.e2m.profile.presentation.profile.composable.ProfileName
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.plus

@Composable
internal fun ProfileScreen() {
    ProfileScaffold()
}

@Composable
private fun ProfileScaffold(
    modifier: Modifier = Modifier,
) {
    val current = LocalDensity.current
    val headerCollapsedSize = remember { mutableStateOf(Size.Zero) }

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        E2MBackgroundDark()

        E2MScaffold(
            topBar = {
                E2MHeader(
                    modifier = Modifier.onGloballyPositioned {
                        headerCollapsedSize.value = it.size.toSize()
                    },
                    leadingIconId = R.drawable.ic_menu,
                    trailingIconId = R.drawable.ic_search,
                    title = getString().profileTxt,
                )
            },
            content = {
                ProfileContent(
                    offsetYBanner = with(current) { headerCollapsedSize.value.height.toDp() }
                )
            }
        )
    }
}

@Composable
private fun ProfileContent(
    modifier: Modifier = Modifier,
    offsetYBanner: Dp,
) {
    val size = E2MTheme.alias.size
    val color = E2MTheme.alias.color
    val maxImageSize = size.icon.largeX
    val minImageSize = size.icon.large
    val itemsProfileUser = ProfileUserData(
        bannerId = R.drawable.img_song,
        avatarId = R.drawable.img_profile,
        fullname = "Phan Khắc Mạnh",
        numberFollowers = 242,
        numberFollowing = 1
    )

    var currentImageSize by remember { mutableStateOf(maxImageSize) }
    var imageScale by remember { mutableFloatStateOf(1f) }

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                val delta = available.y
                val newImageSize = currentImageSize + delta.dp
                val previousImageSize = currentImageSize

                currentImageSize = newImageSize.coerceIn(minImageSize, maxImageSize)
                val consumed = currentImageSize - previousImageSize

                imageScale = currentImageSize / maxImageSize

                return Offset(0f, consumed.value)
            }
        }
    }

    val itemsLikeList = persistentListOf<E2MSongsData>().plus(
        List(5) {
            E2MSongsData(
                imageId = R.drawable.img_song,
                name = "Tên bài hát $it",
                singer = "Tên nghệ sĩ $it",
            )
        }
    )

    val itemsMyMusicList = persistentListOf(
        ProfileMyMusicData(
            imageId = R.drawable.img_song,
            isAlbum = true,
            name = "Album của bạn",
            view = 17200,
            numberSongs = 6,
        ),
        ProfileMyMusicData(
            imageId = R.drawable.img_song,
            isAlbum = false,
            name = "Bài hát của bạn",
            view = 2000,
            numberSongs = 1,
        ),
        ProfileMyMusicData(
            imageId = R.drawable.img_song,
            isAlbum = false,
            name = "Bài hát của bạn",
            view = 4200,
            numberSongs = 1,
        ),
        ProfileMyMusicData(
            imageId = R.drawable.img_song,
            isAlbum = true,
            name = "Album của bạn",
            view = 28100,
            numberSongs = 16,
        ),
        ProfileMyMusicData(
            imageId = R.drawable.img_song,
            isAlbum = false,
            name = "Bài hát của bạn",
            view = 11300,
            numberSongs = 1,
        ),
    )

    Box(
        Modifier.nestedScroll(nestedScrollConnection)
    ) {
        LazyColumn(
            modifier = modifier
                .padding(
                    top = size.spacing.large8x,
                    bottom = currentImageSize,
                )
                .offset {
                    IntOffset(0, currentImageSize.roundToPx())
                },
            contentPadding = PaddingValues(vertical = size.spacing.large),
            verticalArrangement = Arrangement.spacedBy(size.spacing.large)
        ) {
            item {
                ProfileMyMusic(
                    items = itemsMyMusicList,
                )
            }

            item {
                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = size.spacing.small),
                    thickness = size.stroke.thick,
                    color = color.border.blackLight
                )
            }

            item {
                ProfileLikes(
                    modifier = Modifier.padding(horizontal = size.spacing.small),
                    items = itemsLikeList,
                )
            }

            item {
                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = size.spacing.small),
                    thickness = size.stroke.thick,
                    color = color.border.blackLight,
                )
            }

            item {
                ProfileButton(
                    items = fakeProfileButtonData()
                )
            }
        }

        ProfileBanner(
            modifier = Modifier
                .height(currentImageSize + size.spacing.large3X)
                .align(Alignment.TopCenter)
                .offset(0.dp, -offsetYBanner),
            items = itemsProfileUser,
        )


        Box(
            modifier = Modifier.align(Alignment.TopCenter),
            contentAlignment = Alignment.Center
        ) {
            ProfileAvatar(
                modifier = Modifier
                    .padding(top = size.spacing.large)
                    .size(maxImageSize)
                    .graphicsLayer {
                        scaleX = imageScale
                        scaleY = imageScale
                        translationY = -(maxImageSize.toPx() - currentImageSize.toPx()) / 2f
                    },
                items = itemsProfileUser,
            )

            ProfileName(
                modifier = modifier.offset {
                    IntOffset(0, (currentImageSize - size.spacing.large).roundToPx())
                },
                items = itemsProfileUser,
            )
        }
    }
}
