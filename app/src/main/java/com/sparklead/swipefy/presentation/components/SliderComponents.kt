@file:OptIn(ExperimentalMaterial3Api::class)

package com.sparklead.swipefy.presentation.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.sparklead.swipefy.presentation.theme.Grey
import com.sparklead.swipefy.presentation.theme.LightGreen

@Composable
fun SongSlider(onSlideChange: (Float) -> Unit, sliderPosition: Float) {

    val interactionSource = MutableInteractionSource()
    Slider(
        modifier = Modifier.padding(start = 10.dp, end = 10.dp),
        value = sliderPosition,
        onValueChange = { onSlideChange(it) },
        valueRange = 0f..100f,
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