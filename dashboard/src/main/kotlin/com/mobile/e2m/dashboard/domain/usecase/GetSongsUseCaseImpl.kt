package com.mobile.e2m.dashboard.domain.usecase

import com.mobile.e2m.core.datasource.remote.firebase.data.entity.SongsEntity
import kotlinx.coroutines.flow.Flow

class GetSongsUseCaseImpl(
    private val songsRepository: com.mobile.e2m.home.domain.repository.SongsRepository
) : GetSongsUseCase {
    override fun invoke(): Flow<Result<List<SongsEntity>>> {
        return songsRepository.getAllSongs()
    }
}
