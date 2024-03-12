package com.sparklead.swipefy.presentation.sign_up

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sparklead.core.data.model.User
import com.sparklead.swipefy.common.Resource
import com.sparklead.swipefy.domain.use_case.ConfirmPasswordValidationUseCase
import com.sparklead.swipefy.domain.use_case.EmailValidationUseCase
import com.sparklead.swipefy.domain.use_case.NameValidationUseCase
import com.sparklead.swipefy.domain.use_case.PasswordValidationUseCase
import com.sparklead.swipefy.domain.use_case.SaveUserToFirebaseUseCase
import com.sparklead.swipefy.domain.use_case.SaveUserToLocalDbUseCase
import com.sparklead.swipefy.domain.use_case.SignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val useCase: SignUpUseCase,
    private val nameValidationUseCase: NameValidationUseCase,
    private val emailValidationUseCase: EmailValidationUseCase,
    private val passwordValidationUseCase: PasswordValidationUseCase,
    private val confirmPasswordValidationUseCase: ConfirmPasswordValidationUseCase,
    private val saveUserToFirebaseUseCase: SaveUserToFirebaseUseCase,
    private val saveUserToLocalDbUseCase: SaveUserToLocalDbUseCase
) : ViewModel() {

    private val _signUpUiState = MutableStateFlow<SignUpUiState>(SignUpUiState.Empty)
    val signUpUiState = _signUpUiState.asStateFlow()

    var validationState by mutableStateOf(SignUpValidationState())

    fun onValidationEvent(event: SignUpValidationEvent) {
        when (event) {
            is SignUpValidationEvent.NameChanged -> {
                validationState = validationState.copy(name = event.name)
            }

            is SignUpValidationEvent.EmailChanged -> {
                validationState = validationState.copy(email = event.email)
            }

            is SignUpValidationEvent.PasswordChanged -> {
                validationState = validationState.copy(password = event.password)
            }

            is SignUpValidationEvent.ConfirmPasswordChanged -> {
                validationState = validationState.copy(confirmPassword = event.confirmPassword)
            }

            is SignUpValidationEvent.Register -> {
                checkValidation()
            }
        }
    }

    private fun checkValidation() {
        val nameResult = nameValidationUseCase.invoke(validationState.name)
        val emailResult = emailValidationUseCase.invoke(validationState.email)
        val passwordResult = passwordValidationUseCase.invoke(validationState.password)
        val confirmPasswordResult = confirmPasswordValidationUseCase.invoke(
            validationState.password,
            validationState.confirmPassword
        )

        val hasError = listOf(
            nameResult,
            emailResult,
            passwordResult,
            confirmPasswordResult
        ).any { !it.success }

        if (hasError) {
            validationState = validationState.copy(
                nameError = nameResult.message,
                emailError = emailResult.message,
                passwordError = passwordResult.message,
                confirmPasswordError = confirmPasswordResult.message
            )
            return
        }
        viewModelScope.launch {
            signUpUser(validationState.name, validationState.email, validationState.password)
        }
    }

    private fun signUpUser(username: String, email: String, password: String) =
        viewModelScope.launch(Dispatchers.IO) {
            useCase(email, password).collect { result ->
                when (result) {
                    is Resource.Error -> _signUpUiState.value =
                        SignUpUiState.Error(result.message ?: "An unexpected error occurred")

                    is Resource.Loading -> _signUpUiState.value = SignUpUiState.Loading
                    is Resource.Success -> result.data?.user?.let {
                        saveUserToDatabase(
                            it.uid,
                            username,
                            email
                        )
                    }
                }
            }
        }

    private fun saveUserToDatabase(id: String, username: String, email: String) =
        viewModelScope.launch(Dispatchers.IO) {
            saveUserToFirebaseUseCase(id, username, email).collect { result ->
                when (result) {
                    is Resource.Error -> {
                        SignUpUiState.Error(result.message ?: "An unexpected error occurred")
                    }

                    is Resource.Loading -> {

                    }

                    is Resource.Success -> {
//                        _signUpUiState.value = SignUpUiState.Success()
                        result.data?.let { saveUserToLocalDb(it) }
                    }
                }
            }
        }

    private fun saveUserToLocalDb(user: User) = viewModelScope.launch(Dispatchers.IO) {
        try {
            saveUserToLocalDbUseCase(user)
            _signUpUiState.value = SignUpUiState.Success
        } catch (e : Exception) {
            _signUpUiState.value = SignUpUiState.Error(e.message.toString())
        }
    }
}