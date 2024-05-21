package com.sparklead.swipefy.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.sparklead.swipefy.R
import com.sparklead.core.data.model.Song
import com.sparklead.swipefy.presentation.theme.DarkGrey
import com.sparklead.swipefy.presentation.theme.Grey

@Composable
fun SwipefyMiniPlayer(
    song: Song,
    download: (Song) -> Unit,
    play: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(6.dp),
        colors = CardDefaults.cardColors(DarkGrey)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(56.dp)
                    .padding(10.dp)
                    .weight(1f),
                model = song.imageUrl,
                contentDescription = null
            )
            Column(
                Modifier
                    .weight(2f)
            ) {
                Text(
                    text = song.name.replace(
                        Regex("(\\s?-\\s?|\\s?\\(\\s?|\\s?\\[\\s?).*$"),
                        ""
                    ).trim(), color = Color.White
                )
                Text(
                    text = song.artist[0].name,
                    color = Color.Gray,
                    fontWeight = FontWeight.Light
                )
            }
            Row(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            ) {
                IconButton(onClick = { download(song) }) {
                    AsyncImage(
                        modifier = Modifier.size(25.dp),
                        model = R.drawable.download,
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(Color.White)
                    )
                }
                IconButton(onClick = { play() }) {
                    AsyncImage(
                        modifier = Modifier.size(23.dp),
                        model = R.drawable.play,
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(
                            Color.White
                        )
                    )
                }
            }

        }
        LinearProgressIndicator(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .height(2.dp)
                .padding(horizontal = 4.dp),
            progress = 0.8f,
            color = Color.White,
            backgroundColor = Grey
        )
    }


}
