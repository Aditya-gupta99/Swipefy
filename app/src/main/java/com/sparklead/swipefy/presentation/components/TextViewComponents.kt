package com.sparklead.swipefy.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sparklead.swipefy.R
import com.sparklead.swipefy.presentation.theme.DarkGreen
import com.sparklead.swipefy.presentation.theme.LightGreen

@Composable
fun NormalTextView(value: String) {
    Text(
        text = value, modifier = Modifier.fillMaxWidth(), style = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal,
            fontFamily = FontFamily(Font(R.font.outfit_regular))
        ), color = Color.White, textAlign = TextAlign.Center
    )
}

@Composable
fun HeadingTextView(value: String) {
    Text(
        text = value, modifier = Modifier.fillMaxWidth(), style = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal,
            fontFamily = FontFamily(Font(R.font.outfit_medium))
        ),
        color = DarkGreen,
        textAlign = TextAlign.Center
    )
}

@Composable
fun DividerTextView(value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
    ) {
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f), color = Color.Gray, thickness = 1.dp
        )
        Text(modifier = Modifier.padding(8.dp), text = value, fontSize = 18.sp, color = Color.White)
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f), color = Color.Gray, thickness = 1.dp
        )
    }
}

@Composable
fun ClickableTextViewLogin(initialText: String, clickText: String, onClick: () -> Unit) {

    val annotatedString = buildAnnotatedString {
        append(initialText)
        withStyle(style = SpanStyle(color = DarkGreen)) {
            pushStringAnnotation(tag = clickText, annotation = clickText)
            append(clickText)
        }
    }

    ClickableText(modifier = Modifier.fillMaxWidth(), style = TextStyle(
        fontSize = 18.sp,
        fontWeight = FontWeight.Normal,
        fontStyle = FontStyle.Normal,
        textAlign = TextAlign.Center,
        color = Color.White
    ), text = annotatedString, onClick = { offset ->
        annotatedString.getStringAnnotations(offset, offset).firstOrNull()?.also {
            if (it.item == clickText) {
                onClick()
            }
        }
    })
}

@Composable
fun LeftAlignNormalText(value: String) {

    Text(
        text = value,
        modifier = Modifier.wrapContentWidth(), style = TextStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal,
            fontFamily = FontFamily(Font(R.font.outfit_regular))
        ),
        color = Color.White,
        textAlign = TextAlign.Left
    )
}

@Composable
fun LeftAlignHeadingText(value: String) {

    Text(
        text = value,
        modifier = Modifier.wrapContentWidth(), style = TextStyle(
            fontSize = 22.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal,
            fontFamily = FontFamily(Font(R.font.outfit_medium))
        ),
        color = Color.White,
        textAlign = TextAlign.Left
    )
}

@Composable
fun SwipefySongHeadingTextView(value: String) {
    Text(
        text = value,
        modifier = Modifier.fillMaxWidth(),
        style = TextStyle(
            fontSize = 17.sp,
            fontWeight = FontWeight.Medium,
            fontStyle = FontStyle.Normal,
            fontFamily = FontFamily(Font(R.font.outfit_medium)),
        ),
        color = Color.White,
        maxLines = 1
    )
}

@Composable
fun SwipefySongArtistTextView(value: String) {
    Text(
        text = value,
        modifier = Modifier.fillMaxWidth(),
        style = TextStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight.Light,
            fontStyle = FontStyle.Normal,
            fontFamily = FontFamily(Font(R.font.outfit_medium))
        ),
        color = Color.Gray,
        maxLines = 1
    )
}

@Composable
fun SwipefyOptionsTextView(value: String) {
    Text(
        text = value,
        modifier = Modifier.fillMaxWidth(),
        style = TextStyle(
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            fontStyle = FontStyle.Normal,
            fontFamily = FontFamily(Font(R.font.outfit_medium)),
        ),
        color = Color.White,
        maxLines = 1
    )
}