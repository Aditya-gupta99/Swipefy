package com.sparklead.swipefy.presentation.home

import com.sparklead.core.data.dto.track.TrackDto
import com.sparklead.core.data.model.SwipeSong
import com.sparklead.core.data.model.User

sealed class HomeUiState {

    object Empty : HomeUiState()

    object Loading : HomeUiState()

    data class Success(val trackDto: TrackDto?) : HomeUiState()

    data class Error(val message: String) : HomeUiState()

    object Ready : HomeUiState()

    data class RandomSongSuccess(val list: List<SwipeSong>) : HomeUiState()

    data class UserSuccess(val user: User?) : HomeUiState()
}