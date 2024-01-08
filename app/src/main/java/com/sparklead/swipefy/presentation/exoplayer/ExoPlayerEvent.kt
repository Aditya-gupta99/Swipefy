package com.sparklead.swipefy.presentation.exoplayer

sealed class ExoPlayerEvent {

    object PlayPause : ExoPlayerEvent()

    object SelectedAudioChanged : ExoPlayerEvent()

    object SeekTo : ExoPlayerEvent()

    data class UpdateProgress(val newProgress: Float) : ExoPlayerEvent()
}