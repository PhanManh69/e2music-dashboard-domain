package com.mobile.e2m.dashboard.presentation

import com.mobile.e2m.core.ui.navigation.route.AppNavigationRoute
import com.mobile.e2m.core.ui.R
import kotlinx.serialization.Serializable

@Serializable
sealed class NavBottomItems<T>(val name: Int, val icon: Int, val route: T) {

    @Serializable
    data object Home : NavBottomItems<AppNavigationRoute.Dashboard.Home>(
        name = com.mobile.e2m.core.ui.R.string.home,
        icon = R.drawable.ic_home_solid,
        route = AppNavigationRoute.Dashboard.Home,
    )

    @Serializable
    data object Music : NavBottomItems<AppNavigationRoute.Dashboard.Music>(
        name = com.mobile.e2m.core.ui.R.string.music,
        icon = R.drawable.ic_songs_solid,
        route = AppNavigationRoute.Dashboard.Music,
    )

    @Serializable
    data object Profile : NavBottomItems<AppNavigationRoute.Dashboard.Profile>(
        name = com.mobile.e2m.core.ui.R.string.profile,
        icon = R.drawable.img_profile,
        route = AppNavigationRoute.Dashboard.Profile,
    )
}
