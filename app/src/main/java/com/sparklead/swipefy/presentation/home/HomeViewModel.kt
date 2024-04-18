@file:OptIn(SavedStateHandleSaveableApi::class)

package com.sparklead.swipefy.presentation.home

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.SavedStateHandleSaveableApi
import androidx.lifecycle.viewmodel.compose.saveable
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import com.sparklead.core.data.model.SwipeSong
import com.sparklead.swipefy.common.Resource
import com.sparklead.swipefy.domain.use_case.GetUserFromLocalDbUseCase
import com.sparklead.swipefy.domain.use_case.RandomTracksUseCase
import com.sparklead.swipefy.domain.use_case.TrackUseCase
import com.sparklead.swipefy.presentation.exoplayer.ExoAudioState
import com.sparklead.swipefy.presentation.exoplayer.ExoPlayerEvent
import com.sparklead.swipefy.presentation.exoplayer.ExoServiceHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: TrackUseCase,
    private val randomTracksUseCase: RandomTracksUseCase,
    private val exoServiceHandler: ExoServiceHandler,
    private val getUserToLocalDbUseCase: GetUserFromLocalDbUseCase,
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
    private var duration by savedStateHandle.saveable { mutableLongStateOf(29000L) }
    var progress by savedStateHandle.saveable { mutableFloatStateOf(0f) }
    var isPlaying by savedStateHandle.saveable { mutableStateOf(false) }
    var currentSong by mutableStateOf<SwipeSong?>(null)

    init {
        getUserFromLocalDb()
        getRandomSongs(getUserGenre())

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
            is HomeUiEvent.PlayPause -> exoServiceHandler.onPlayerEvents(
                ExoPlayerEvent.PlayPause, progress.toLong()
            )

            is HomeUiEvent.SeekTo -> exoServiceHandler.onPlayerEvents(
                ExoPlayerEvent.SeekTo,
                seekPosition = ((duration * uiEvent.position) / 100f).toLong()
            )

            is HomeUiEvent.UpdateProgress -> {
                exoServiceHandler.onPlayerEvents(ExoPlayerEvent.UpdateProgress(uiEvent.newProgress))
            }

            is HomeUiEvent.SelectedMediaChange -> {
                currentSong?.let { buildMediaItem(it) }
                    ?.let { ExoPlayerEvent.SelectedAudioChanged(it) }
                    ?.let { exoServiceHandler.onPlayerEvents(it) }
            }
        }
    }

    private fun buildMediaItem(currentSong: SwipeSong): MediaItem {
        return MediaItem.Builder()
            .setUri(currentSong.previewUrl)
            .setMediaMetadata(
                MediaMetadata.Builder()
                    .setArtworkUri(Uri.parse(currentSong.imageUrl))
                    .setAlbumTitle(currentSong.name)
                    .setDisplayTitle(currentSong.artist[0].name)
                    .build()
            ).build()
    }

    private fun calculateProgressValue(currentProgress: Long) {
        progress =
            if (currentProgress > 0) ((currentProgress.toFloat() / duration.toFloat()) * 100f) else 0f
    }

    private fun getUserGenre(): String {
        // TODO logic to getUser fab Genre
        return "indian"
    }

    private fun getRandomSongs(genre: String) = viewModelScope.launch(Dispatchers.IO) {
        randomTracksUseCase(genre)
            .collect {
                when (it) {
                    is Resource.Error -> _homeUiState.value =
                        HomeUiState.Error(it.message.toString())

                    is Resource.Loading -> _homeUiState.value = HomeUiState.Loading

                    is Resource.Success -> {
                        if (it.data?.size == 0) _homeUiState.value =
                            HomeUiState.Error("No Song Found")
                        else _homeUiState.value = HomeUiState.RandomSongSuccess(it.data ?: listOf())
                    }
                }
            }
    }

    private fun getUserFromLocalDb() = viewModelScope.launch(Dispatchers.IO) {
        getUserToLocalDbUseCase().collect { result ->
            when (result) {
                is Resource.Error -> {

                }

                is Resource.Loading -> {

                }

                is Resource.Success -> {
                    _homeUiState.value = HomeUiState.UserSuccess(result.data)
                }
            }
        }
    }
}