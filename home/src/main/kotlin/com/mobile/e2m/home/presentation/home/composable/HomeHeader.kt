@file:OptIn(ExperimentalMaterial3Api::class)

package com.mobile.e2m.home.presentation.home.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.mobile.e2m.core.ui.composable.debounceClickable
import com.mobile.e2m.core.ui.theme.E2MTheme
import com.mobile.e2m.core.ui.R
import com.mobile.e2m.core.ui.composable.E2MIcon
import com.mobile.e2m.home.presentation.getString

private val DEFAULT_HEIGHT_VALUE = 240.dp

@Composable
internal fun HomeHeader(
    scrollBehavior: TopAppBarScrollBehavior? = null,
    menuOnClick: () -> Unit = { },
    searchOnClick: () -> Unit = { },
) {
    val current = LocalDensity.current
    val headerCollapsedSize = remember { mutableStateOf(Size.Zero) }
    val searchTabSize = remember { mutableStateOf(Size.Zero) }
    val collapsedHeight = with(current) {
        if (headerCollapsedSize.value.height > 0) headerCollapsedSize.value.height.toDp()
        else DEFAULT_HEIGHT_VALUE
    }
    val searchTabHeight = with(current) {
        if (searchTabSize.value.height > 0) searchTabSize.value.height.toDp()
        else DEFAULT_HEIGHT_VALUE
    }

    LargeTopAppBar(
        scrollBehavior = scrollBehavior,
        colors = TopAppBarDefaults.largeTopAppBarColors(
            containerColor = Color.Transparent,
            scrolledContainerColor = Color.Transparent
        ),
        title = {
            HomeButtonSearch(
                modifier = Modifier.onGloballyPositioned {
                    searchTabSize.value = it.size.toSize()
                },
                onClick = { searchOnClick() }
            )
        },
        navigationIcon = {
            HomeHeaderSearch(
                modifier = Modifier
                    .offset(-(E2MTheme.alias.size.spacing.small4x))
                    .onGloballyPositioned {
                        headerCollapsedSize.value = it.size.toSize()
                    },
                scrollBehavior = scrollBehavior,
                iconId = R.drawable.ic_menu,
                title = getString().homeTxt,
                menuOnClick = { menuOnClick() },
                searchOnClick = { searchOnClick() },
            )
        },
        collapsedHeight = collapsedHeight,
        expandedHeight = searchTabHeight + collapsedHeight,
    )
}

@Composable
private fun HomeHeaderSearch(
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    iconId: Int? = null,
    title: String? = null,
    menuOnClick: () -> Unit = { },
    searchOnClick: () -> Unit = { },
) {
    val collapsedFraction = scrollBehavior?.state?.collapsedFraction ?: 0f
    val color = E2MTheme.alias.color
    val size = E2MTheme.alias.size

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = size.spacing.small)
            .padding(top = size.spacing.small2x)
    ) {
        iconId?.let {
            E2MIcon(
                modifier = Modifier
                    .size(size.icon.smallX)
                    .align(Alignment.CenterStart)
                    .debounceClickable { menuOnClick() },
                iconId = iconId,
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            contentAlignment = Alignment.Center,
        ) {
            if (collapsedFraction < 1f) {
                title?.let {
                    Text(
                        text = it,
                        style = E2MTheme.typography.title.black,
                        color = color.text.white,
                        textAlign = TextAlign.Center
                    )
                }
            } else {
                HomeButtonSearch(
                    modifier = Modifier
                        .offset(size.spacing.small4x)
                        .padding(start = size.spacing.large2X),
                    paddingEnd = size.spacing.none,
                    onClick = { searchOnClick() }
                )
            }
        }
    }
}

@Preview
@Composable
fun HomeHeaderPreview() {
    HomeHeader()
}
