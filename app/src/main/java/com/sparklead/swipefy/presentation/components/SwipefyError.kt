package com.sparklead.swipefy.presentation.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.sparklead.swipefy.R
import com.sparklead.swipefy.presentation.theme.DarkGreen
import com.sparklead.swipefy.presentation.theme.DarkGrey
import com.sparklead.swipefy.presentation.theme.LightGreen

@Composable
fun SwipefySweetError(message: String, onclick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(18.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            modifier = Modifier.size(70.dp),
            model = R.drawable.ic_error_black_24dp,
            contentDescription = null,
            colorFilter = ColorFilter.tint(Color.Gray)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Sorry we weren't able to load",
            style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Normal,
                color = White
            )
        )
        Text(
            text = message,
            style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Normal,
                color = White
            )
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = { onclick() },
            contentPadding = PaddingValues(),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isSystemInDarkTheme()) LightGreen else DarkGreen
            )
        ) {
            Text(
                modifier = Modifier.padding(start = 20.dp, end = 20.dp),
                text = "Try Again",
                fontSize = 15.sp
            )
        }
    }
}