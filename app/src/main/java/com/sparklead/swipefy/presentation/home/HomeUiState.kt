package com.sparklead.swipefy.presentation.home

import com.sparklead.swipefy.data.dto.track.TrackDto

sealed class HomeUiState {

    object Empty : HomeUiState()

    object Loading : HomeUiState()

    data class Success(val trackDto: TrackDto?) : HomeUiState()

    data class Error(val message: String) : HomeUiState()

    object Ready : HomeUiState()
}