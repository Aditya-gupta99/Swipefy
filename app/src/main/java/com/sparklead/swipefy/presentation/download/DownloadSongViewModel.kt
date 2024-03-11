package com.sparklead.swipefy.presentation.download

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sparklead.swipefy.domain.use_case.GetDownloadSongUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DownloadSongViewModel @Inject constructor(private val getDownloadSongUseCase: GetDownloadSongUseCase) :
    ViewModel() {

    private val _downloadSongUiState = MutableStateFlow<DownloadSongUiState>(DownloadSongUiState.Loading)
    val downloadSongUiState = _downloadSongUiState.asStateFlow()

    init {
        getDownloadList()
    }

    fun getDownloadList() = viewModelScope.launch {
        getDownloadSongUseCase()
            .catch {
                _downloadSongUiState.value = DownloadSongUiState.Error(it.message.toString())
            }
            .collect {
                _downloadSongUiState.value = DownloadSongUiState.Success(it)
            }
    }

}