package com.mobile.e2m.home.domain.repository

import com.mobile.e2m.core.datasource.local.fakedata.model.AlbumsModel
import kotlinx.coroutines.flow.Flow

interface AlbumsRepository {
    fun getAlbums(): Flow<Result<List<AlbumsModel>>>
}
