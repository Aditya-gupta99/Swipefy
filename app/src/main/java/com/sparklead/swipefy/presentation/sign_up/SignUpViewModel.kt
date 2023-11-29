package com.sparklead.swipefy.presentation.sign_up

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sparklead.swipefy.common.Resource
import com.sparklead.swipefy.domain.use_case.SignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val useCase: SignUpUseCase) : ViewModel() {

    private val _signUpUiState = MutableStateFlow<SignUpUiState>(SignUpUiState.Empty)
    val signUpUiState = _signUpUiState.asStateFlow()

    fun signUpUser(email: String, password: String) = viewModelScope.launch(Dispatchers.IO) {
        _signUpUiState.value = SignUpUiState.Loading
        useCase(email,password).collect { result->
            when(result) {
                is Resource.Error -> _signUpUiState.value = SignUpUiState.Error(result.message?:"An unexpected error occurred")
                is Resource.Loading -> _signUpUiState.value = SignUpUiState.Loading
                is Resource.Success -> _signUpUiState.value = SignUpUiState.Success(result.data?.user?.uid ?: "")
            }
        }
    }
}