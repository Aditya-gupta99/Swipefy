@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.sparklead.swipefy.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sparklead.swipefy.R
import com.sparklead.swipefy.ui.theme.Black
import com.sparklead.swipefy.ui.theme.Grey

@Composable
fun NormalEditTextView(labelValue: String, painterResources: Painter) {

    val textValue = remember {
        mutableStateOf("")
    }

    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = textValue.value,
        onValueChange = {
            textValue.value = it
        },
        textStyle = TextStyle(
            fontSize = 20.sp
        ),
        leadingIcon = {
            Icon(painter = painterResources, contentDescription = "textImageIcon", tint = Black)
        },
        colors = TextFieldDefaults.textFieldColors(
//            containerColor = Grey,
            unfocusedLabelColor = Grey,
            focusedLabelColor = Grey,
            cursorColor = Black,
            textColor = Black,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        shape = RoundedCornerShape(22.dp),
        label = { Text(text = labelValue) },
        keyboardOptions = KeyboardOptions.Default
    )
}


@Composable
fun PasswordTextView(labelValue: String, painterResources: Painter) {

    val passwordValue = remember {
        mutableStateOf("")
    }

    val passwordVisible = remember {
        mutableStateOf(false)
    }

    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = passwordValue.value,
        onValueChange = {
            passwordValue.value = it
        },
        textStyle = TextStyle(
            fontSize = 20.sp
        ),
        leadingIcon = {
            Icon(painter = painterResources, contentDescription = "textImageIcon", tint = Black)
        },
        colors = TextFieldDefaults.textFieldColors(
//            containerColor = Grey,
            unfocusedLabelColor = Grey,
            focusedLabelColor = Grey,
            cursorColor = Black,
            textColor = Black,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        shape = RoundedCornerShape(22.dp),
        label = { Text(text = labelValue) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            val imageIcon =
                if (passwordVisible.value) Icons.Filled.VisibilityOff else Icons.Filled.Visibility
            val description =
                if (passwordVisible.value) stringResource(id = R.string.hide_password) else stringResource(
                    id = R.string.show_password
                )

            IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                Icon(imageVector = imageIcon, contentDescription = description)
            }
        },
        visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation()
    )
}