package com.mobile.e2m.home.data.repository

import com.mobile.e2m.core.datasource.local.fakedata.AlbumsLocalDataSource
import com.mobile.e2m.core.datasource.local.fakedata.model.AlbumsModel
import com.mobile.e2m.home.domain.repository.AlbumsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AlbumsRepositoryImpl(
    private val albumsLocalDataSource: AlbumsLocalDataSource
) : AlbumsRepository {
    override fun getAlbums(): Flow<Result<List<AlbumsModel>>> = flow {
        try {
            val albumsList = albumsLocalDataSource.getAlbums()
            emit(Result.success(albumsList))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
}
