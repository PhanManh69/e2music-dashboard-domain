package com.mobile.e2m.dashboard.di

import com.mobile.e2m.dashboard.data.repository.SongsRepositoryImpl
import com.mobile.e2m.dashboard.domain.repository.SongsRepository
import com.mobile.e2m.dashboard.domain.usecase.GetSongsUseCase
import com.mobile.e2m.dashboard.domain.usecase.GetSongsUseCaseImpl
import com.mobile.e2m.dashboard.presentation.DashboardViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val dashboardModule = module {
    viewModel { DashboardViewModel(get(), get(), get()) }

    single { GetSongsUseCaseImpl(get()) } bind GetSongsUseCase::class

    single { SongsRepositoryImpl(get()) } bind SongsRepository::class
}
