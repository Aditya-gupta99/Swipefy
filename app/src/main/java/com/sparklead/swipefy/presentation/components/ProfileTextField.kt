package com.sparklead.swipefy.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.sparklead.swipefy.R


@Composable
fun ProfileTextField(headerText: String? = null, footerText: Int) {
    Column {
        if (headerText !== null) {
            Text(
                text = headerText,
                color = Color.White,
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.outfit_medium))
                )
            )
        }
        Text(
            text = stringResource(footerText),
            color = Color.White,
            style = TextStyle(
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.outfit_medium))
            )
        )
    }
}