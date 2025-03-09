package com.mobile.e2m.home.presentation.home

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.mobile.e2m.core.ui.base.E2MBaseViewModel
import com.mobile.e2m.home.domain.usecase.GetAlbumsUseCase
import com.mobile.e2m.home.domain.usecase.GetSongsUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getAlbumsUseCase: GetAlbumsUseCase,
    private val getSongsUseCase: GetSongsUseCase,
) : E2MBaseViewModel<HomeState, Unit, Unit>(
    initialState = HomeState()
) {

    init {
        getAlbumsInfo()
        getSongsInfo()
    }

    override fun handleAction(action: Unit) {

    }

    private fun getAlbumsInfo() {
        viewModelScope.launch {
            getAlbumsUseCase.invoke()
                .onStart {
                    Log.d("EManh Debug", "Get Albums Info: OnStart")
                }

                .catch {
                    Log.d("EManh Debug", "Get Albums Info: ${it.message}")
                }

                .collect {
                    it.onSuccess { data ->
                        Log.d("EManh Debug", "Get Albums Info: $data")

                        data.let {
                            mutableStateFlow.update {
                                state.copy(
                                    albumsList = data
                                )
                            }
                        }
                    }

                    it.onFailure { error ->
                        Log.d("EManh Debug", "Get Albums Info: ${error.message}")
                    }
                }
        }
    }

    private fun getSongsInfo() {
        viewModelScope.launch {
            getSongsUseCase.invoke()
                .onStart {
                    Log.d("EManh Debug", "Get Songs Info: OnStart")
                    mutableStateFlow.update { it.copy(isLoadingSong = true) }
                }
                .catch { error ->
                    Log.d("EManh Debug", "Get Songs Info: ${error.message}")
                    mutableStateFlow.update { it.copy(isLoadingSong = false) }
                }
                .collect { result ->
                    result.onSuccess { data ->
                        Log.d("EManh Debug", "Get Songs Info: $data")
                        data.let {
                            mutableStateFlow.update {
                                state.copy(
                                    songsList = data,
                                    isLoadingSong = false,
                                )
                            }
                        }
                    }

                    result.onFailure { error ->
                        Log.d("EManh Debug", "Get Songs Info: ${error.message}")
                        mutableStateFlow.update { it.copy(isLoadingSong = false) }
                    }
                }
        }
    }
}
