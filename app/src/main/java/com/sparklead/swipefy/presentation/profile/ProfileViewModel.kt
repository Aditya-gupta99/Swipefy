package com.sparklead.swipefy.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sparklead.swipefy.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor (private val repository: AuthRepository) : ViewModel() {

    var profileUiState = MutableStateFlow<ProfileUiState>(ProfileUiState.Loading)
        private set

    fun logout() = viewModelScope.launch {
        repository.signOutUser()
    }
}