package com.mobile.e2m.home.domain.usecase

import com.mobile.e2m.core.datasource.local.fakedata.model.AlbumsModel
import kotlinx.coroutines.flow.Flow

interface GetAlbumsUseCase {
    operator fun invoke(): Flow<Result<List<AlbumsModel>>>
}
