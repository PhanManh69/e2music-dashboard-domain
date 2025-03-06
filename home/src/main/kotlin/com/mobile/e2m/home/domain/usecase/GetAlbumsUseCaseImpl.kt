package com.mobile.e2m.home.domain.usecase

import com.mobile.e2m.core.datasource.local.fakedata.model.AlbumsModel
import com.mobile.e2m.home.domain.repository.AlbumsRepository
import kotlinx.coroutines.flow.Flow

class GetAlbumsUseCaseImpl(
    private val albumsRepository: AlbumsRepository
) : GetAlbumsUseCase {
    override fun invoke(): Flow<Result<List<AlbumsModel>>> {
        return albumsRepository.getAlbums()
    }
}
