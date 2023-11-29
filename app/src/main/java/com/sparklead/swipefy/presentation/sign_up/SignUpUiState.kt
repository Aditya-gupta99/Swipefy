package com.sparklead.swipefy.presentation.sign_up

data class SignUpUiState(

    val isLoading: Boolean = false,

    val error: String = "",

    val success: String? = null
)