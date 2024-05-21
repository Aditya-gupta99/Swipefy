@file:OptIn(SavedStateHandleSaveableApi::class)

package com.sparklead.swipefy.presentation.song_list

import android.os.AsyncTask
import android.util.Log
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.SavedStateHandleSaveableApi
import androidx.lifecycle.viewmodel.compose.saveable
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.compose.collectAsLazyPagingItems
import com.sparklead.core.data.model.Song
import com.sparklead.swipefy.domain.use_case.DownloadSongUseCase
import com.sparklead.swipefy.domain.use_case.GetRecommendedSongsUseCase
import com.sparklead.swipefy.presentation.exoplayer.ExoPlayerEvent
import com.sparklead.swipefy.presentation.exoplayer.ExoServiceHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SongListViewModel @Inject constructor(
    private val getRecommendedSongsUseCase: GetRecommendedSongsUseCase,
    private val downloadSongUseCase: DownloadSongUseCase,
    private val player: ExoPlayer,
    savedStateHandle: SavedStateHandle,
    private val exoServiceHandler: ExoServiceHandler,
) : ViewModel() {

    init {
        getFavoriteArtist()
    }

    private val _songListUiState = MutableStateFlow<PagingData<Song>>(PagingData.empty())
    val songListUiState = _songListUiState.asStateFlow().cachedIn(viewModelScope)

    fun getFavoriteArtist() {
        getRecommendedSongs()
    }

    private fun getRecommendedSongs() = viewModelScope.launch(Dispatchers.IO) {
        getRecommendedSongsUseCase()
            .collect {
                _songListUiState.value = it
            }
    }

    fun downloadSong(song: Song) = viewModelScope.launch {
        downloadSongUseCase(song)
    }


    //audio
    private var duration by savedStateHandle.saveable { mutableLongStateOf(29000L) }
    var progress by savedStateHandle.saveable { mutableFloatStateOf(0f) }
    var isPlaying by savedStateHandle.saveable { mutableStateOf(false) }
    var songIndex by savedStateHandle.saveable { mutableIntStateOf(0) }


    fun playSong() {

    }

    fun onPlayMusic(value: Int) {
        songIndex = value

    }

    fun convertToMediaItem( ) {
        val musicList =
    }

    fun convertToMediaItems(): List<MediaItem> {
        val mediaItems = mutableListOf<MediaItem>()
        val pad = _songListUiState.value
        pad.forEach { musicItem ->
            // Assuming MusicItem contains necessary information like URI or URL
            val mediaItem = MediaItem.fromUri(musicItem.uri) // or fromUriString if using URL
            // Set any other metadata like title, artist, etc., if available
            mediaItems.add(mediaItem)
        }
        return mediaItems
    }



    override fun onCleared() {
        viewModelScope.launch {
            exoServiceHandler.onPlayerEvents(ExoPlayerEvent.PlayPause)
        }
    }

    fun NewFun ( ) {
        AsyncTask.execute({
            Thread.sleep(1000)
            Log.e("@@@","AsyncTask")
        })

        AsyncTask.execute({
            Thread.sleep(3000)
            Log.e("@@@","Another")
        })
    }

}