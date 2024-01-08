package com.sparklead.swipefy.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sparklead.swipefy.presentation.theme.DarkGreen
import com.sparklead.swipefy.presentation.theme.LightGreen

@Composable
fun GradiantButton(value: String, onclick: () -> Unit) {

    Button(
        onClick = { onclick() },
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp),
        contentPadding = PaddingValues()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(48.dp)
                .background(
                    brush = Brush.horizontalGradient(listOf(DarkGreen, LightGreen)),
                    shape = RoundedCornerShape(50.dp)
                ),
            contentAlignment = Alignment.Center,
        ) {
            Text(text = value, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun CircularIconButton(image: ImageVector, onButtonClick: () -> Unit) {

    IconButton(
        onClick = { onButtonClick() },
        modifier = Modifier
            .width(50.dp)
            .height(50.dp)
            .shadow(
                elevation = 30.dp,
                shape = RoundedCornerShape(50.dp),
                spotColor = LightGreen,
                ambientColor = LightGreen
            ),
    ) {
        Box(
            modifier = Modifier
                .width(50.dp)
                .heightIn(50.dp)
                .background(
                    brush = Brush.horizontalGradient(listOf(DarkGreen, LightGreen)),
                    shape = RoundedCornerShape(50.dp)
                ),
            contentAlignment = Alignment.Center,
        ) {
            Icon(imageVector = image, contentDescription = "button")
        }
    }
}