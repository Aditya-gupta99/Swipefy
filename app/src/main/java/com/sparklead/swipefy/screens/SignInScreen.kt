package com.sparklead.swipefy.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sparklead.swipefy.R
import com.sparklead.swipefy.components.ClickableTextViewLogin
import com.sparklead.swipefy.components.DividerTextView
import com.sparklead.swipefy.components.GradiantButton
import com.sparklead.swipefy.components.HeadingTextView
import com.sparklead.swipefy.components.NormalEditTextView
import com.sparklead.swipefy.components.NormalTextView
import com.sparklead.swipefy.components.PasswordTextView
import com.sparklead.swipefy.navigation.Screen
import com.sparklead.swipefy.ui.theme.Black

@Composable
fun SignInScreen(navController: NavController) {

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
            NormalEditTextView(labelValue = "Email", painterResource(id = R.drawable.ic_email))
            Spacer(modifier = Modifier.height(15.dp))
            PasswordTextView(
                labelValue = "Password",
                painterResources = painterResource(id = R.drawable.ic_password)
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