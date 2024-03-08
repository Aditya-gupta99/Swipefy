package com.sparklead.swipefy.presentation.sign_in

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sparklead.swipefy.common.Resource
import com.sparklead.swipefy.domain.use_case.EmailValidationUseCase
import com.sparklead.swipefy.domain.use_case.PasswordValidationUseCase
import com.sparklead.swipefy.domain.use_case.SignInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val emailValidationUseCase: EmailValidationUseCase,
    private val passwordValidationUseCase: PasswordValidationUseCase
) : ViewModel() {

    private val _signInUiState = MutableStateFlow<SignInUiState>(SignInUiState.Empty)
    val signInUiState = _signInUiState.asStateFlow()

    var validationState by mutableStateOf(SignInValidationState())

    fun onValidate(event: SignInValidationEvent) {
        when (event) {
            is SignInValidationEvent.EmailChanged -> {
                validationState = validationState.copy(email = event.email)
            }

            is SignInValidationEvent.Login -> {
                checkValidation()
            }

            is SignInValidationEvent.PasswordChanged -> {
                validationState = validationState.copy(password = event.password)
            }
        }
    }

    private fun checkValidation() {
        val emailResult = emailValidationUseCase(validationState.email)
        val passwordResult = passwordValidationUseCase(validationState.password)

        val hasError = listOf(
            emailResult, passwordResult
        ).any { !it.success }

        if (hasError) {
            validationState = validationState.copy(
                emailError = emailResult.message,
                passwordError = passwordResult.message
            )
            return
        }
        viewModelScope.launch {
            signInUser(validationState.email, validationState.password)
        }
    }


    private fun signInUser(email: String, password: String) =
        viewModelScope.launch(Dispatchers.IO) {
            signInUseCase(email, password).collect { result ->
                when (result) {
                    is Resource.Error -> {
                        _signInUiState.value =
                            SignInUiState.Error(result.message ?: "An unexpected error occurred")
                    }

                    is Resource.Loading -> {
                        _signInUiState.value = SignInUiState.Loading
                    }

                    is Resource.Success -> {
                        _signInUiState.value = SignInUiState.Success(result.data?.user?.uid ?: "")
                    }
                }
            }
        }

}