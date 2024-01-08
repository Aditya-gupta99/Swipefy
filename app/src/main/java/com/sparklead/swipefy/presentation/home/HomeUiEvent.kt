package com.sparklead.swipefy.presentation.home

sealed class HomeUiEvent {

    object PlayPause : HomeUiEvent()

    data class SeekTo(val position: Float) : HomeUiEvent()

    data class UpdateProgress(val newProgress: Float) : HomeUiEvent()

    object SelectedMediaChange : HomeUiEvent()
}