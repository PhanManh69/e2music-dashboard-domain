package com.mobile.e2m.dashboard.presentation.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mobile.e2m.core.ui.theme.E2MTheme
import com.mobile.e2m.dashboard.presentation.NavBottomItems

@Composable
internal fun DashboardNavButton(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    val bottomPadding = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()
    val context = LocalContext.current
    val size = E2MTheme.alias.size
    val color = E2MTheme.alias.color
    val bottomScreens = remember {
        listOf(
            NavBottomItems.Home,
            NavBottomItems.Music,
            NavBottomItems.Profile
        )
    }

    Column(
        modifier = modifier.padding(bottom = bottomPadding)
    ) {
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = size.stroke.thick,
            color = color.surface.white,
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(size.spacing.small),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination

            bottomScreens.forEachIndexed { index, item ->
                val isSelected = currentDestination?.hierarchy?.any {
                    it.route == item.route::class.qualifiedName
                } == true

                DashboardBottomNavItem(
                    selected = isSelected,
                    icon = item.icon,
                    name = context.getString(item.name),
                    isProfile = index == 2,
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
    }
}

@Preview
@Composable
fun NavButtonPreview() {
    DashboardNavButton(
        navController = rememberNavController()
    )
}
