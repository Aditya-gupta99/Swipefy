package com.sparklead.swipefy.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sparklead.swipefy.common.Resource
import com.sparklead.swipefy.domain.use_case.TrackUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val useCase: TrackUseCase) : ViewModel() {

    private val _homeUiState = MutableStateFlow<HomeUiState>(HomeUiState.Empty)
    val homeUiState = _homeUiState.asStateFlow()

    fun getTrackById(id: String) = viewModelScope.launch {
        useCase(id).collect { result ->
            when (result) {
                is Resource.Error -> {
                    _homeUiState.value = HomeUiState.Error(result.message.toString())
                }

                is Resource.Loading -> {
                    _homeUiState.value = HomeUiState.Loading
                }

                is Resource.Success -> {
                    _homeUiState.value = HomeUiState.Success(result.data)
                }
            }
        }
    }
}