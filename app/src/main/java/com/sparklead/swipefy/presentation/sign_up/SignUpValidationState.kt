package com.sparklead.swipefy.presentation.sign_up

data class SignUpValidationState(
    val name: String = "",

    val nameError: String? = null,

    val email: String = "",

    val emailError: String? = null,

    val password: String = "",

    val passwordError: String? = null,

    val confirmPassword: String = "",

    val confirmPasswordError: String? = null
)