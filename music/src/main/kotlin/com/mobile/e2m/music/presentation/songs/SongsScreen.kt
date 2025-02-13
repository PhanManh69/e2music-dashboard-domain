package com.mobile.e2m.music.presentation.songs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.mobile.e2m.core.ui.R
import com.mobile.e2m.core.ui.composable.E2MSelectionLoad
import com.mobile.e2m.core.ui.composable.E2MSongsData
import com.mobile.e2m.core.ui.composable.E2MSongsItem
import com.mobile.e2m.music.presentation.songs.composable.SongLoadItem
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.plus
import kotlinx.coroutines.delay

@Composable
internal fun SongsScreen() {
    SongsScaffold()
}

@Composable
private fun SongsScaffold(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = { },
) {
    val itemsList = persistentListOf<E2MSongsData>().plus(
        List(5000) {
            E2MSongsData(
                imageId = R.drawable.img_song,
                name = "Tên bài hát $it",
                singer = "Tên nghệ sĩ $it",
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
        isLoading = isLoading,
        items = itemsList,
        itemContentComposable = {
            E2MSongsItem(
                iconId = R.drawable.ic_play_circle,
                songItem = it,
                iconOnClick = { onClick() }
            )
        },
        itemLoadComposable = {
            SongLoadItem()
        }
    )
}
