package com.sparklead.swipefy.presentation.song_list

import android.app.PendingIntent
import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.session.MediaSession
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sparklead.core.data.model.Song
import com.sparklead.swipefy.domain.use_case.DownloadSongUseCase
import com.sparklead.swipefy.domain.use_case.GetRecommendedSongsUseCase
import com.sparklead.swipefy.presentation.exoplayer.notification.MusicNotificationManager
import com.sparklead.swipefy.presentation.exoplayer.PlayerNotificationListener
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SongListViewModel @Inject constructor(
    private val getRecommendedSongsUseCase: GetRecommendedSongsUseCase,
    private val downloadSongUseCase: DownloadSongUseCase,
    private val player: ExoPlayer
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


    private lateinit var notificationManager: MusicNotificationManager

    lateinit var mediaSession: MediaSession
    private val serviceJob = SupervisorJob()
    private val serviceScope = CoroutineScope(Dispatchers.Main + serviceJob)

    private var isStarted = false

    fun onStart() {

    }

}