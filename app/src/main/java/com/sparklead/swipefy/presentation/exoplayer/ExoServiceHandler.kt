@file:OptIn(DelicateCoroutinesApi::class)

package com.sparklead.swipefy.presentation.exoplayer

import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ExoServiceHandler @Inject constructor(private val exoPlayer: ExoPlayer) : Player.Listener {

    private val _exoAudioState = MutableStateFlow<ExoAudioState>(ExoAudioState.Initial)
    val exoAudioState = _exoAudioState.asStateFlow()

    private var job: Job? = null

    private fun addMediaItem(mediaItem: MediaItem) {
        exoPlayer.setMediaItem(mediaItem)
        exoPlayer.prepare()
    }

    private fun addMediaItems(mediaList :List<MediaItem>) {
        exoPlayer.setMediaItems(mediaList)
        exoPlayer.prepare()
    }

    suspend fun onPlayerEvents(
        playerEvent: ExoPlayerEvent,
        seekPosition: Long = 0
    ) {
        when (playerEvent) {
            is ExoPlayerEvent.PlayPause -> playOrPause()

            is ExoPlayerEvent.SeekTo -> exoPlayer.seekTo(seekPosition)

            is ExoPlayerEvent.SelectedAudioChanged -> {
                addMediaItem(playerEvent.mediaItem)
                _exoAudioState.value = ExoAudioState.Playing(isPlaying = true)
//                exoPlayer.playWhenReady = true
                updateProgress()
            }

            is ExoPlayerEvent.UpdateProgress -> {
                exoPlayer.seekTo((exoPlayer.duration * playerEvent.newProgress).toLong())
            }
        }
    }

    override fun onPlaybackStateChanged(playbackState: Int) {
        when (playbackState) {
            ExoPlayer.STATE_BUFFERING -> _exoAudioState.value =
                ExoAudioState.Buffering(exoPlayer.currentPosition)

            ExoPlayer.STATE_READY -> _exoAudioState.value = ExoAudioState.Ready(exoPlayer.duration)
        }
    }

    override fun onIsPlayingChanged(isPlaying: Boolean) {
        _exoAudioState.value = ExoAudioState.Playing(isPlaying = isPlaying)
        _exoAudioState.value = ExoAudioState.CurrentPlaying(exoPlayer.currentMediaItemIndex)
        if (isPlaying) {
            GlobalScope.launch(Dispatchers.IO) {
                updateProgress()
            }
        } else {
            stopProgressUpdate()
        }
    }

    private suspend fun playOrPause() {
        if (exoPlayer.isPlaying) {
            exoPlayer.pause()
            stopProgressUpdate()
        } else {
            exoPlayer.play()
            _exoAudioState.value = ExoAudioState.Playing(isPlaying = true)
            updateProgress()
        }
    }

    private suspend fun updateProgress() = job.run {
        while (true) {
            delay(500)
            _exoAudioState.value = ExoAudioState.Progress(exoPlayer.currentPosition)
        }
    }

    private fun stopProgressUpdate() {
        job?.cancel()
        _exoAudioState.value = ExoAudioState.Playing(isPlaying = false)
    }
}