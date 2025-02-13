package com.mobile.e2m.music.data.local

import com.mobile.e2m.core.ui.R
import com.mobile.e2m.music.presentation.genres.composable.GenreData
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

fun fakeGenresData(): PersistentList<GenreData> {
    val itemsList = persistentListOf(
        GenreData(
            imageId = R.drawable.img_classical,
            title = R.string.classical,
            numberSongs = 24,
        ),
        GenreData(
            imageId = R.drawable.img_country,
            title = R.string.country,
            numberSongs = 72,
        ),
        GenreData(
            imageId = R.drawable.img_edm,
            title = R.string.edm,
            numberSongs = 51,
        ),
        GenreData(
            imageId = R.drawable.img_jazz_blues,
            title = R.string.jazzBlues,
            numberSongs = 38,
        ),
        GenreData(
            imageId = R.drawable.img_pop,
            title = R.string.pop,
            numberSongs = 44,
        ),
        GenreData(
            imageId = R.drawable.img_rap_hiphop,
            title = R.string.rapHipHop,
            numberSongs = 92,
        ),
        GenreData(
            imageId = R.drawable.img_rock,
            title = R.string.rock,
            numberSongs = 53,
        ),
        GenreData(
            imageId = R.drawable.img_soul_randb,
            title = R.string.soulRandB,
            numberSongs = 53,
        )
    )

    return itemsList
}
