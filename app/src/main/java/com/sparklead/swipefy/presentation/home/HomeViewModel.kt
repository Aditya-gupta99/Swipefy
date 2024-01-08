@file:OptIn(SavedStateHandleSaveableApi::class)

package com.sparklead.swipefy.presentation.home

import android.net.Uri
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.SavedStateHandleSaveableApi
import androidx.lifecycle.viewmodel.compose.saveable
import androidx.media3.common.MediaItem
import com.sparklead.swipefy.common.Resource
import com.sparklead.swipefy.domain.use_case.TrackUseCase
import com.sparklead.swipefy.presentation.exoplayer.ExoAudioState
import com.sparklead.swipefy.presentation.exoplayer.ExoPlayerEvent
import com.sparklead.swipefy.presentation.exoplayer.ExoServiceHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: TrackUseCase,
    private val exoServiceHandler: ExoServiceHandler,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

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


    //audio
    var duration by savedStateHandle.saveable { mutableLongStateOf(0L) }
    var progress by savedStateHandle.saveable { mutableFloatStateOf(0f) }
    var isPlaying by savedStateHandle.saveable { mutableStateOf(false) }
    var currentSong by savedStateHandle.saveable { mutableStateOf<MediaItem>(MediaItem.fromUri(Uri.EMPTY)) }

    init {
        viewModelScope.launch {
            exoServiceHandler.exoAudioState.collectLatest { mediaState ->
                when (mediaState) {
                    is ExoAudioState.Buffering -> {}

                    is ExoAudioState.CurrentPlaying -> {}

                    is ExoAudioState.Initial -> {}

                    is ExoAudioState.Playing -> isPlaying = mediaState.isPlaying

                    is ExoAudioState.Progress -> calculateProgressValue(mediaState.progress)

                    is ExoAudioState.Ready -> {
                        duration = mediaState.duration
                        _homeUiState.value = HomeUiState.Ready
                    }
                }
            }
        }
    }

    fun onUiEvents(uiEvent: HomeUiEvent) = viewModelScope.launch {
        when (uiEvent) {
            is HomeUiEvent.PlayPause -> exoServiceHandler.onPlayerEvents(ExoPlayerEvent.PlayPause)

            is HomeUiEvent.SeekTo -> exoServiceHandler.onPlayerEvents(
                ExoPlayerEvent.SeekTo,
                seekPosition = ((duration * uiEvent.position) / 100f).toLong()
            )

            is HomeUiEvent.UpdateProgress -> {}

            is HomeUiEvent.SelectedMediaChange -> {
                exoServiceHandler.addMediaItem(currentSong)
            }
        }
    }

    private fun calculateProgressValue(currentProgress: Long) {
        progress =
            if (currentProgress > 0) ((currentProgress.toFloat() / duration.toFloat()) * 100f) else 0f
    }

}