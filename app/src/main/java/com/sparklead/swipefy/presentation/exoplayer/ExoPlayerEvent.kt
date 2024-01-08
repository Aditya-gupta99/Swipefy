package com.sparklead.swipefy.presentation.exoplayer

import androidx.media3.common.MediaItem

sealed class ExoPlayerEvent {

    object PlayPause : ExoPlayerEvent()

    data class SelectedAudioChanged(val mediaItem: MediaItem) : ExoPlayerEvent()

    object SeekTo : ExoPlayerEvent()

    data class UpdateProgress(val newProgress: Float) : ExoPlayerEvent()
}