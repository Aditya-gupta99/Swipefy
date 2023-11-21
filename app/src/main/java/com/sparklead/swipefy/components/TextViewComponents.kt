package com.sparklead.swipefy.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sparklead.swipefy.R
import com.sparklead.swipefy.ui.theme.Black
import com.sparklead.swipefy.ui.theme.Purple40

@Composable
fun NormalTextView(value: String) {
    Text(
        text = value,
        modifier = Modifier.fillMaxWidth(),
        style = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal
        ),
        color = Black,
        textAlign = TextAlign.Center

    )
}

@Composable
fun HeadingTextView(value: String) {
    Text(
        text = value,
        modifier = Modifier.fillMaxWidth(),
        style = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal
        ),
        color = Black,
        textAlign = TextAlign.Center

    )
}

@Composable
fun DividerTextView(value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f), color = Color.Gray, thickness = 1.dp
        )
        Text(modifier = Modifier.padding(8.dp), text = value, fontSize = 18.sp)
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f), color = Color.Gray, thickness = 1.dp
        )
    }
}

@Composable
fun ClickableTextView() {
    val initialText = "Already have an account ? "
    val loginText = stringResource(id = R.string.login)

    val annotatedString = buildAnnotatedString {
        append(initialText)
        withStyle(style = SpanStyle(color = Purple40)) {
            pushStringAnnotation(tag = loginText, annotation = loginText)
            append(loginText)
        }
    }

    ClickableText(
        modifier = Modifier.fillMaxWidth(),
        style = TextStyle(
            fontSize = 20.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Center
        ),
        text = annotatedString, onClick = {offset->
        annotatedString.getStringAnnotations(offset,offset)
            .firstOrNull()?.also {
                if(it.item== loginText) {

                }
            }
    })
}