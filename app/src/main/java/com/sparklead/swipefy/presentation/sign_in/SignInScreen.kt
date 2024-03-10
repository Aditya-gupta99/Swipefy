@file:OptIn(ExperimentalMaterial3Api::class)

package com.sparklead.swipefy.presentation.sign_in

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.sparklead.swipefy.R
import com.sparklead.swipefy.presentation.components.ClickableTextViewLogin
import com.sparklead.swipefy.presentation.components.DividerTextView
import com.sparklead.swipefy.presentation.components.GradiantButton
import com.sparklead.swipefy.presentation.components.HeadingTextView
import com.sparklead.swipefy.presentation.components.NormalTextView
import com.sparklead.swipefy.presentation.components.SwipefyEditTextView
import com.sparklead.swipefy.presentation.navigation.Screen
import com.sparklead.swipefy.presentation.theme.Black
import com.sparklead.swipefy.presentation.theme.Grey
import com.sparklead.swipefy.presentation.theme.LightGreen

@Composable
fun SignInScreen(navController: NavController) {

    val signInViewModel: SignInViewModel = hiltViewModel()
    val state = signInViewModel.signInUiState.collectAsState().value
    val validationState = signInViewModel.validationState

    var passwordVisibility: Boolean by rememberSaveable { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }
    val showDialog = rememberSaveable { mutableStateOf(false) }

    when (state) {
        is SignInUiState.Empty -> {}
        is SignInUiState.Error -> {
            showDialog.value = false
            LaunchedEffect(key1 = state.message) {
                snackbarHostState.showSnackbar(state.message)
            }
        }

        is SignInUiState.Loading -> {
            showDialog.value = true
        }

        is SignInUiState.Success -> {
            showDialog.value = false
            navController.navigate(Screen.HomeScreen.route)
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(18.dp),
        containerColor = Black,
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { padding ->
        Column(
            modifier = Modifier.padding(padding)
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            NormalTextView(value = stringResource(id = R.string.hey_there))
            Spacer(modifier = Modifier.height(6.dp))
            HeadingTextView(value = stringResource(id = R.string.welcome_back))
            Spacer(modifier = Modifier.height(30.dp))
            SwipefyEditTextView(
                labelValue = "Email",
                painterResource(id = R.drawable.ic_email),
                validationState.email,
                validationState.emailError
            ) {
                signInViewModel.onValidate(SignInValidationEvent.EmailChanged(it))
            }
            Spacer(modifier = Modifier.height(15.dp))
            SwipefyEditTextView(
                labelValue = "Password",
                painterResources = painterResource(id = R.drawable.ic_password),
                validationState.password,
                validationState.passwordError,
                trailingIcon = {
                    val imageIcon =
                        if (passwordVisibility) Icons.Filled.VisibilityOff else Icons.Filled.Visibility
                    val description =
                        if (passwordVisibility) stringResource(id = R.string.hide_password) else stringResource(
                            id = R.string.show_password
                        )

                    IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                        Icon(imageVector = imageIcon, contentDescription = description, tint = Grey)
                    }
                },
                visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation()
            ) {
                signInViewModel.onValidate(SignInValidationEvent.PasswordChanged(it))
            }
            Spacer(modifier = Modifier.height(200.dp))
            GradiantButton(value = "Login") {
                signInViewModel.onValidate(SignInValidationEvent.Login)
            }
            Spacer(modifier = Modifier.height(40.dp))
            DividerTextView(value = "or")
            Spacer(modifier = Modifier.height(40.dp))
            ClickableTextViewLogin(
                initialText = "Don't have an account yet? ",
                clickText = "Register"
            ) {
                navController.navigate(Screen.SignUpScreen.route)
            }
        }
        if (showDialog.value) {
            Dialog(
                onDismissRequest = { showDialog.value },
                properties = DialogProperties(
                    dismissOnBackPress = false,
                    dismissOnClickOutside = false
                )
            ) {
                CircularProgressIndicator(color = LightGreen)
            }
        }
    }

}