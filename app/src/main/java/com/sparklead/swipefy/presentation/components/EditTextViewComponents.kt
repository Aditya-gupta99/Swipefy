@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.sparklead.swipefy.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sparklead.swipefy.R
import com.sparklead.swipefy.presentation.theme.DarkGrey
import com.sparklead.swipefy.presentation.theme.Grey

@Composable
fun SwipefyEditTextView(
    labelValue: String,
    painterResources: Painter,
    textValue: String,
    error: String?,
    trailingIcon: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onValueChanged: (String) -> Unit,
) {
    Column {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = textValue,
            onValueChange = { onValueChanged(it) },
            isError = error != null,
            textStyle = TextStyle(
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.outfit_regular))
            ),
            leadingIcon = {
                Icon(
                    painter = painterResources,
                    contentDescription = "textImageIcon",
                    tint = Color.White
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = DarkGrey,
                unfocusedLabelColor = Grey,
                focusedLabelColor = Grey,
                cursorColor = Color.White,
                textColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(22.dp),
            label = { Text(text = labelValue) },
            keyboardOptions = KeyboardOptions.Default,
            trailingIcon = trailingIcon,
            visualTransformation = visualTransformation,
            singleLine = true,
            maxLines = 1
        )
        if (error != null) {
            Text(
                text = error,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 10.dp),
                fontFamily = FontFamily(Font(R.font.outfit_regular)),
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.End,
            )
        } else {
            Spacer(modifier = Modifier.height(12.dp))
        }
    }

}
