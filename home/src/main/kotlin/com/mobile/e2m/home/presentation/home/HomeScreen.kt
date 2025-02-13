@file:OptIn(ExperimentalMaterial3Api::class)

package com.mobile.e2m.home.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import com.mobile.e2m.core.ui.R
import com.mobile.e2m.core.ui.composable.E2MScaffold
import com.mobile.e2m.core.ui.composable.E2MSongsData
import com.mobile.e2m.core.ui.composable.E2MSongsItem
import com.mobile.e2m.core.ui.composable.background.E2MBackgroundDark
import com.mobile.e2m.core.ui.theme.E2MTheme
import com.mobile.e2m.home.presentation.getString
import com.mobile.e2m.home.presentation.home.composable.HomeHeader
import com.mobile.e2m.home.presentation.home.composable.HomeRecommendAlbum
import com.mobile.e2m.home.presentation.home.composable.HomeRecommendSong
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.plus

@Composable
internal fun HomeScreen() {
    HomeScaffold()
}

@Composable
private fun HomeScaffold(
    modifier: Modifier = Modifier,
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        E2MBackgroundDark()

        E2MScaffold(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                HomeHeader(
                    scrollBehavior = scrollBehavior,
                )
            },
            content = {
                HomeContent()
            }
        )
    }
}

@Composable
private fun HomeContent(
    modifier: Modifier = Modifier,
    playSongOnClick: () -> Unit = { },
    favouriteSongOnClick: () -> Unit = { },
) {
    val size = E2MTheme.alias.size
    val color = E2MTheme.alias.color
    val style = E2MTheme.typography
    val itemsList = persistentListOf<E2MSongsData>().plus(
        List(50) {
            E2MSongsData(
                imageId = R.drawable.img_song,
                name = "Tên bài hát $it",
                singer = "Tên nghệ sĩ $it",
            )
        }
    )

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = size.spacing.large),
    ) {
        item {
            HomeRecommendAlbum()
        }

        item {
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = size.spacing.small,
                        vertical = size.spacing.large,
                    ),
                thickness = size.stroke.thick,
                color = color.border.blackLight
            )
        }

        item {
            HomeRecommendSong()
        }

        item {
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = size.spacing.small,
                        vertical = size.spacing.large,
                    ),
                thickness = size.stroke.thick,
                color = color.border.blackLight
            )
        }

        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = size.spacing.small)
                    .padding(bottom = size.spacing.small),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = getString().recentlyPlayedTxt,
                    style = style.title.bold,
                    color = color.text.white,
                )

                Text(
                    text = getString().playedAllTxt,
                    style = style.base.bold,
                    color = color.text.blur2Light,
                )
            }
        }

        items(itemsList.size) {
            E2MSongsItem(
                modifier = Modifier
                    .padding(horizontal = size.spacing.small)
                    .padding(bottom = if (it < itemsList.size - 1) size.spacing.small2x else size.spacing.none),
                iconId = R.drawable.ic_favourite,
                shape = RoundedCornerShape(size.radius.radius2),
                blur = size.stroke.thickX,
                songItem = itemsList[it],
                imageOnClick = { playSongOnClick() },
                iconOnClick = { favouriteSongOnClick() },
            )
        }
    }
}
