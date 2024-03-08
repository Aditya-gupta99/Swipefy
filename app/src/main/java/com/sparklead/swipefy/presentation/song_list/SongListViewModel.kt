package com.sparklead.swipefy.presentation.song_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.sparklead.swipefy.domain.model.Song
import com.sparklead.swipefy.domain.use_case.GetRecommendedSongsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SongListViewModel @Inject constructor(
    private val getRecommendedSongsUseCase: GetRecommendedSongsUseCase
) :
    ViewModel() {
    init {
        getRecommendedSongs()
    }

    private val _songListUiState = MutableStateFlow<PagingData<Song>>(PagingData.empty())
    val songListUiState = _songListUiState.asStateFlow()

    fun getFavoriteArtist() {

    }

    private fun getRecommendedSongs() = viewModelScope.launch(Dispatchers.IO) {
        getRecommendedSongsUseCase()
            .collect {
                _songListUiState.value = it
            }
    }
}