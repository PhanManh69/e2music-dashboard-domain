package com.mobile.e2m.music.presentation.artists

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.mobile.e2m.core.ui.R
import com.mobile.e2m.core.ui.composable.E2MSelectionLoad
import com.mobile.e2m.music.presentation.artists.composable.ArtistData
import com.mobile.e2m.music.presentation.artists.composable.ArtistItem
import com.mobile.e2m.music.presentation.artists.composable.ArtistLoadItem
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.plus
import kotlinx.coroutines.delay

@Composable
internal fun ArtistsScreen(
    checkMiniPlayer: Boolean = false,
) {
    ArtistsScaffold(
        checkMiniPlayer = checkMiniPlayer,
    )
}

@Composable
private fun ArtistsScaffold(
    modifier: Modifier = Modifier,
    checkMiniPlayer: Boolean = false,
) {
    val itemsList = persistentListOf<ArtistData>().plus(
        List(24) {
            ArtistData(
                imageId = R.drawable.img_song,
                name = "Tên nghệ sĩ $it",
                numberAlbums = 5,
                numberSongs = 20,
            )
        }
    )

    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        delay(2000L)
        isLoading = false
    }

    E2MSelectionLoad(
        modifier = modifier,
        checkMiniPlayer = checkMiniPlayer,
        isLoading = isLoading,
        items = itemsList,
        numberElementsLoad = 7,
        itemContentComposable = {
            ArtistItem(
                items = it,
            )
        },
        itemLoadComposable = {
            ArtistLoadItem()
        }
    )
}
