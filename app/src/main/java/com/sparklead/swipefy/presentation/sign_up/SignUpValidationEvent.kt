package com.sparklead.swipefy.presentation.sign_up

sealed class SignUpValidationEvent {

    data class NameChanged(val name: String) : SignUpValidationEvent()

    data class EmailChanged(val email: String) : SignUpValidationEvent()

    data class PasswordChanged(val password: String) : SignUpValidationEvent()

    data class ConfirmPasswordChanged(val confirmPassword: String) : SignUpValidationEvent()

    object Register : SignUpValidationEvent()
}