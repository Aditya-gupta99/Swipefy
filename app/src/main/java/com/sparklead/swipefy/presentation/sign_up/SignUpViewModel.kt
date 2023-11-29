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
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val useCase: SignUpUseCase) : ViewModel() {

    private val _signUpUiState = mutableStateOf(SignUpUiState())
    val signUpUiState : State<SignUpUiState> = _signUpUiState

    fun signUpUser(email: String, password: String) = viewModelScope.launch(Dispatchers.IO) {
        useCase(email,password).collect { result->
            when(result) {
                is Resource.Error -> _signUpUiState.value = SignUpUiState(error= result.message ?: "An unexpected error occurred")
                is Resource.Loading -> _signUpUiState.value = SignUpUiState(isLoading = true)
                is Resource.Success -> _signUpUiState.value = SignUpUiState(success = result.data?.user?.uid)
            }
        }
    }
}