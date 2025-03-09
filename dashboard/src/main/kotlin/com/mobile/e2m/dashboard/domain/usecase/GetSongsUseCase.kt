package com.mobile.e2m.dashboard.domain.usecase

import com.mobile.e2m.core.datasource.remote.firebase.data.entity.SongsEntity
import kotlinx.coroutines.flow.Flow

interface GetSongsUseCase {
    operator fun invoke(): Flow<Result<List<SongsEntity>>>
}
