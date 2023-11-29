package com.sparklead.swipefy.presentation.sign_up

import com.google.firebase.auth.AuthResult

sealed class SignUpUiState {

    object Empty : SignUpUiState()

    object Loading : SignUpUiState()

    data class Success(val user: String) : SignUpUiState()

    data class Error(val message: String) : SignUpUiState()
}