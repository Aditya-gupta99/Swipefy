@file:OptIn(ExperimentalMaterial3Api::class)

package com.sparklead.swipefy.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.sparklead.swipefy.ui.theme.Grey
import com.sparklead.swipefy.ui.theme.LightGreen

@Composable
fun SongSlider() {

    val sliderPosition = remember {
        mutableFloatStateOf(3f)
    }
    val interactionSource = MutableInteractionSource()
    Slider(
        modifier = Modifier.padding(start = 10.dp, end = 10.dp),
        value = sliderPosition.floatValue,
        onValueChange = { sliderPosition.floatValue = it },
        valueRange = 0f..10f,
        onValueChangeFinished = {
            // pause music
        },
        colors = SliderDefaults.colors(
            activeTrackColor = LightGreen,
            inactiveTrackColor = Grey
        ),
        thumb = {
            SliderDefaults.Thumb(
                colors = SliderDefaults.colors(LightGreen),
                interactionSource = interactionSource,
                thumbSize = DpSize(0.dp, 0.dp)
            )
        },
    )
}