package com.mobile.e2m.dashboard.domain.repository

import com.mobile.e2m.core.datasource.remote.firebase.data.entity.SongsEntity
import kotlinx.coroutines.flow.Flow

interface SongsRepository {
    fun getAllSongs(): Flow<Result<List<SongsEntity>>>
}
