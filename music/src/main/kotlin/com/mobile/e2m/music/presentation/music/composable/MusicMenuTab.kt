package com.mobile.e2m.music.presentation.music.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.mobile.e2m.core.ui.composable.debounceClickable
import com.mobile.e2m.core.ui.theme.E2MTheme
import com.mobile.e2m.music.presentation.music.MenuTabItems

@Composable
internal fun MusicMenuTab(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val context = LocalContext.current
    val size = E2MTheme.alias.size
    val color = E2MTheme.alias.color
    val menuTab = remember {
        listOf(
            MenuTabItems.Song,
            MenuTabItems.Playlist,
            MenuTabItems.Album,
            MenuTabItems.Artist,
            MenuTabItems.Genre,
        )
    }

    Column(
        modifier = modifier.padding(horizontal = size.spacing.small)
    ) {
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(size.spacing.small)
        ) {
            itemsIndexed(menuTab) { _, item ->
                val isSelected = currentDestination?.hierarchy?.any {
                    it.route == item.route::class.qualifiedName
                } == true

                MusicMenuTabItem(
                    selected = isSelected,
                    title = context.getString(item.name),
                    onClick = {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }

        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .offset(size.spacing.none, -(size.stroke.thin)),
            thickness = size.stroke.thin,
            color = color.border.blackLight
        )
    }
}

@Composable
private fun MusicMenuTabItem(
    modifier: Modifier = Modifier,
    selected: Boolean,
    title: String? = null,
    onClick: () -> Unit = { },
) {
    val current = LocalDensity.current
    val widthTextSize = remember { mutableStateOf(Size.Zero) }
    val size = E2MTheme.alias.size
    val color = E2MTheme.alias.color
    val style = E2MTheme.typography

    Column(
        modifier = modifier.debounceClickable { onClick() },
    ) {
        title?.let {
            Text(
                modifier = Modifier
                    .onGloballyPositioned { value ->
                        widthTextSize.value = value.size.toSize()
                    },
                text = it,
                style = style.title.bold,
                color = if (selected) color.text.blur2Light else color.text.white,
            )
        }

        HorizontalDivider(
            modifier = Modifier
                .width(with(current) { widthTextSize.value.width.toDp() })
                .padding(top = size.spacing.small4x)
                .alpha(if (selected) 1f else 0f),
            thickness = size.stroke.thick,
            color = color.border.blur2Light,
        )
    }
}

@Preview
@Composable
fun MusicMenuTabItemPreview() {
    MusicMenuTabItem(
        selected = true,
        title = "Menu tab",
    )
}
