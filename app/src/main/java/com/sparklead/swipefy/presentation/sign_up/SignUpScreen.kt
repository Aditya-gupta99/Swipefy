@file:OptIn(ExperimentalMaterial3Api::class)

package com.sparklead.swipefy.presentation.sign_up

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
import com.sparklead.swipefy.presentation.components.NormalEditTextView
import com.sparklead.swipefy.presentation.components.NormalTextView
import com.sparklead.swipefy.presentation.components.PasswordTextView
import com.sparklead.swipefy.presentation.navigation.Screen
import com.sparklead.swipefy.presentation.theme.Black

@Composable
fun SignUpScreen(navController: NavController) {

    val signUpViewModel: SignUpViewModel = hiltViewModel()

    val name = rememberSaveable { mutableStateOf("") }
    val email = rememberSaveable { mutableStateOf("") }
    val password = rememberSaveable { mutableStateOf("") }
    val passwordConfirm = rememberSaveable { mutableStateOf("") }
    val showDialog = rememberSaveable { mutableStateOf(false) }
    val launched = rememberSaveable { mutableStateOf(false) }



    val scope = rememberCoroutineScope()
    val state = signUpViewModel.signUpUiState
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = state.value.error) {
        showDialog.value = false
        if (state.value.error.isNotBlank()) {
            snackbarHostState.showSnackbar(state.value.error)
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(18.dp),
        containerColor = Black,
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
    ) {
        Column(modifier = Modifier.padding(it)) {
            Spacer(modifier = Modifier.height(10.dp))
            NormalTextView(value = stringResource(id = R.string.hey_there))
            Spacer(modifier = Modifier.height(6.dp))
            HeadingTextView(value = stringResource(id = R.string.create_an_account))
            Spacer(modifier = Modifier.height(30.dp))
            NormalEditTextView(
                labelValue = "Name",
                painterResource(id = R.drawable.ic_person),
                name
            )
            Spacer(modifier = Modifier.height(15.dp))
            NormalEditTextView(
                labelValue = "Email",
                painterResource(id = R.drawable.ic_email),
                email
            )
            Spacer(modifier = Modifier.height(15.dp))
            PasswordTextView(
                labelValue = "Password",
                painterResources = painterResource(id = R.drawable.ic_password), password
            )
            Spacer(modifier = Modifier.height(15.dp))
            PasswordTextView(
                labelValue = "Confirm password",
                painterResources = painterResource(id = R.drawable.ic_password), passwordConfirm
            )
            Spacer(modifier = Modifier.height(80.dp))
            GradiantButton(value = "Register") {
                signUpViewModel.signUpUser(email.value, password.value)
                showDialog.value = true
                launched.value = !launched.value
            }
            Spacer(modifier = Modifier.height(40.dp))
            DividerTextView(value = "or")
            Spacer(modifier = Modifier.height(40.dp))
            ClickableTextViewLogin(
                initialText = "Already have an account ? ",
                clickText = "Login"
            ) {
                navController.navigate(Screen.SignInScreen.route)
            }
        }
        if (showDialog.value) {
            Dialog(
                onDismissRequest = { showDialog.value },
                DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
            ) {
                Box(
                    contentAlignment= Center,
                    modifier = Modifier
                        .size(100.dp)
                        .background(White, shape = RoundedCornerShape(8.dp))
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}