@file:OptIn(ExperimentalMaterial3Api::class)

package com.mobile.e2m.home.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mobile.e2m.core.ui.composable.E2MHeader
import com.mobile.e2m.core.ui.composable.E2MScaffold
import com.mobile.e2m.core.ui.composable.background.E2MBackgroundDark
import com.mobile.e2m.core.ui.theme.E2MTheme
import com.mobile.e2m.core.ui.theme.Write100
import com.mobile.e2m.home.R
import com.mobile.e2m.home.presentation.home.composable.HomeHeader
import com.mobile.e2m.home.presentation.home.composable.HomeRecentlyPlayedSong
import com.mobile.e2m.home.presentation.home.composable.HomeRecommendAlbum
import com.mobile.e2m.home.presentation.home.composable.HomeRecommendSong

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
private fun HomeContent() {
    val size = E2MTheme.alias.size
    val color = E2MTheme.alias.color

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(size.spacing.large),
    ) {
        HomeRecommendAlbum()

        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = size.spacing.small),
            thickness = size.stroke.thick,
            color = color.border.blackLight
        )

        HomeRecommendSong()

        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = size.spacing.small),
            thickness = size.stroke.thick,
            color = color.border.blackLight
        )

        HomeRecentlyPlayedSong()
    }
}
