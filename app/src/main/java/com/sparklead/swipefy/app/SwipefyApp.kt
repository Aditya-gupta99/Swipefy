package com.sparklead.swipefy.app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sparklead.swipefy.screens.SignUpScreen

@Composable
fun SwipefyApp() {
    Surface(modifier = Modifier.fillMaxSize()) {
        SignUpScreen()
    }
}