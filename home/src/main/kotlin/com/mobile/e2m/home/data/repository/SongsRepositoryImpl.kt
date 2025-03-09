package com.mobile.e2m.home.data.repository

import com.mobile.e2m.core.datasource.remote.firebase.data.SongsFirebaseDataSource
import com.mobile.e2m.core.datasource.remote.firebase.data.entity.SongsEntity
import com.mobile.e2m.home.domain.repository.SongsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SongsRepositoryImpl(
    private val songsFirebaseDataSource: SongsFirebaseDataSource
) : SongsRepository {

    override fun getAllSongs(): Flow<Result<List<SongsEntity>>> = flow {
        try {
            songsFirebaseDataSource.fetchMediaData()
            val songsList = songsFirebaseDataSource.getAllSongs()
            emit(Result.success(songsList))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
}
