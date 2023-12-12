package com.sparklead.swipefy.presentation.sign_in

sealed class SignInValidationEvent {

    data class EmailChanged(val email: String) : SignInValidationEvent()

    data class PasswordChanged(val password: String) : SignInValidationEvent()

    object Login : SignInValidationEvent()
}