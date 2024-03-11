package com.sparklead.swipefy.presentation.download

import com.sparklead.core.data.model.Song

sealed class DownloadSongUiState {

    object Loading : DownloadSongUiState()

    data class Success(val list: List<Song>) : DownloadSongUiState()

    data class Error(val error: String) : DownloadSongUiState()
}