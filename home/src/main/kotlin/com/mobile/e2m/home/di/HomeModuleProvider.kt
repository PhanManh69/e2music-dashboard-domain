package com.mobile.e2m.home.di

import com.mobile.e2m.home.data.repository.AlbumsRepositoryImpl
import com.mobile.e2m.home.data.repository.SongsRepositoryImpl
import com.mobile.e2m.home.domain.repository.AlbumsRepository
import com.mobile.e2m.home.domain.repository.SongsRepository
import com.mobile.e2m.home.domain.usecase.GetAlbumsUseCase
import com.mobile.e2m.home.domain.usecase.GetAlbumsUseCaseImpl
import com.mobile.e2m.home.domain.usecase.GetSongsUseCase
import com.mobile.e2m.home.domain.usecase.GetSongsUseCaseImpl
import com.mobile.e2m.home.presentation.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val homeModule = module {
    viewModel { HomeViewModel(get(), get()) }

    single { GetAlbumsUseCaseImpl(get()) } bind GetAlbumsUseCase::class

    single { AlbumsRepositoryImpl(get()) } bind AlbumsRepository::class

    single { GetSongsUseCaseImpl(get()) } bind GetSongsUseCase::class

    single { SongsRepositoryImpl(get()) } bind SongsRepository::class
}
