package com.sparklead.swipefy.app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sparklead.swipefy.components.StatusBarColor
import com.sparklead.swipefy.screens.SignUpScreen
import com.sparklead.swipefy.ui.theme.AppBlack

@Composable
fun SwipefyApp() {
    Surface(modifier = Modifier.fillMaxSize(), color = AppBlack) {
        StatusBarColor(color = AppBlack)
        SignUpScreen()
    }
}