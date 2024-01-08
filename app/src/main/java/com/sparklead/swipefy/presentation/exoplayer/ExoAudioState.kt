package com.sparklead.swipefy.presentation.exoplayer

sealed class ExoAudioState {

    object Initial : ExoAudioState()

    data class Ready(val duration: Long) : ExoAudioState()

    data class Progress(val progress: Long) : ExoAudioState()

    data class Buffering(val progress: Long) : ExoAudioState()

    data class Playing(val isPlaying: Boolean) : ExoAudioState()

    data class CurrentPlaying(val mediaItemIndex: Int) : ExoAudioState()

}