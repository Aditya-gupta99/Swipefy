package com.sparklead.swipefy.presentation.sign_in

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
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
fun SignInScreen(navController: NavController) {

    val email = rememberSaveable { mutableStateOf("") }
    val password = rememberSaveable { mutableStateOf("") }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(18.dp),
        color = Black
    ) {
        Column {
            Spacer(modifier = Modifier.height(10.dp))
            NormalTextView(value = stringResource(id = R.string.hey_there))
            Spacer(modifier = Modifier.height(6.dp))
            HeadingTextView(value = stringResource(id = R.string.welcome_back))
            Spacer(modifier = Modifier.height(30.dp))
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
            Spacer(modifier = Modifier.height(200.dp))
            GradiantButton(value = "Register") {
                navController.navigate(Screen.HomeScreen.route)
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
    }

}