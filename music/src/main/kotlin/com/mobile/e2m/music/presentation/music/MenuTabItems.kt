package com.mobile.e2m.music.presentation.music

import com.mobile.e2m.core.ui.navigation.route.AppNavigationRoute
import kotlinx.serialization.Serializable

@Serializable
sealed class MenuTabItems<T>(val name: Int, val route: T) {

    @Serializable
    data object Song : MenuTabItems<AppNavigationRoute.Dashboard.Music.Song>(
        name = com.mobile.e2m.core.ui.R.string.song,
        route = AppNavigationRoute.Dashboard.Music.Song,
    )

    @Serializable
    data object Playlist : MenuTabItems<AppNavigationRoute.Dashboard.Music.Playlist>(
        name = com.mobile.e2m.core.ui.R.string.playlist,
        route = AppNavigationRoute.Dashboard.Music.Playlist,
    )

    @Serializable
    data object Album : MenuTabItems<AppNavigationRoute.Dashboard.Music.Album>(
        name = com.mobile.e2m.core.ui.R.string.album,
        route = AppNavigationRoute.Dashboard.Music.Album,
    )

    @Serializable
    data object Artist : MenuTabItems<AppNavigationRoute.Dashboard.Music.Artist>(
        name = com.mobile.e2m.core.ui.R.string.artist,
        route = AppNavigationRoute.Dashboard.Music.Artist,
    )

    @Serializable
    data object Genre : MenuTabItems<AppNavigationRoute.Dashboard.Music.Genre>(
        name = com.mobile.e2m.core.ui.R.string.genre,
        route = AppNavigationRoute.Dashboard.Music.Genre,
    )
}
