package com.sparklead.swipefy.presentation.sign_in

data class SignInValidationState(

    val email: String = "",

    val emailError: String? = null,

    val password: String = "",

    val passwordError: String? = null
)