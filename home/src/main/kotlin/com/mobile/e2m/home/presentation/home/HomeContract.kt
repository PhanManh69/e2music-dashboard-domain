package com.mobile.e2m.home.presentation.home

import com.mobile.e2m.core.datasource.local.fakedata.model.AlbumsModel
import com.mobile.e2m.core.datasource.remote.firebase.data.entity.SongsEntity

data class HomeState(
    val isLoadingSong: Boolean = false,
    val albumsList: List<AlbumsModel> = mutableListOf(),
    val songsList: List<SongsEntity> = mutableListOf(),
)
