package com.sparklead.swipefy.presentation.sign_in

sealed class SignInUiState {

    object Empty : SignInUiState()

    object Loading : SignInUiState()

    data class Error(val message: String) : SignInUiState()

    data class Success(val user: String) : SignInUiState()
}