package com.sparklead.swipefy.presentation.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.pratyush.swipeablecard.ExperimentalSwipeGestureApi
import com.pratyush.swipeablecard.enums.Direction
import com.pratyush.swipeablecard.swipeableCard
import com.sparklead.swipefy.R
import com.sparklead.swipefy.data.dto.track.TrackDto
import com.sparklead.swipefy.presentation.theme.Black
import com.sparklead.swipefy.presentation.theme.DarkGreen
import com.sparklead.swipefy.presentation.theme.DarkGrey
import com.sparklead.swipefy.presentation.theme.Grey

@Composable
fun SmallIconButton(image: ImageVector) {

    Card(
        modifier = Modifier
            .height(40.dp)
            .width(40.dp),
        colors = CardDefaults.cardColors(containerColor = DarkGrey)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(6.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = image,
                contentDescription = "Localized description",
                tint = DarkGreen
            )
        }
    }
}


@Composable
fun SwipeCard(songList: List<TrackDto>, currentIndex: MutableIntState) {
    val lastIndex = songList.lastIndex
//    val currentIndex = rememberSaveable { mutableIntStateOf(0) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(460.dp)
            .background(Black),
        contentAlignment = Alignment.Center
    ) {
        songList.reversed().forEachIndexed { _, profile ->
            SongCard(
                profile = profile,
                onSwipe = {
                    Log.d("DIRECTION", it.name)
                    currentIndex.intValue++
                }
            )
        }
        if (currentIndex.intValue > lastIndex) {
            Text(
                text = "All Cards Swiped",
                textAlign = TextAlign.Center,
                style = TextStyle(fontSize = 22.sp, color = Color.White)
            )
        }
    }
}

@OptIn(ExperimentalSwipeGestureApi::class)
@Composable
fun SongCard(profile: TrackDto, onSwipe: (Direction) -> Unit, modifier: Modifier = Modifier) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = DarkGrey,
        ),
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .swipeableCard(
                onSwipe = onSwipe,
                enableSpringEffect = true
            ),
        elevation = CardDefaults.cardElevation(10.dp),

        ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val painter = painterResource(id = R.drawable.demo_poster)
            AsyncImage(
                modifier = Modifier
                    .aspectRatio(painter.intrinsicSize.width / painter.intrinsicSize.height)
                    .padding(16.dp)
                    .clip(RoundedCornerShape(percent = 4)),
                model = profile.tracks[0].album.images[0].url,
                contentDescription = "poster"
            )
            Text(
                text = profile.tracks[0].name,
                modifier = Modifier.fillMaxWidth(),
                style = TextStyle(
                    fontSize = 24.sp,
                    fontFamily = FontFamily(Font(R.font.outfit_medium))
                ),
                color = Color.White,
                textAlign = TextAlign.Center
            )
            Text(
                text = profile.tracks[0].artists[0].name,
                modifier = Modifier.fillMaxWidth(),
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.outfit_regular))
                ),
                color = Grey,
                textAlign = TextAlign.Center
            )
            SongSlider()
        }
    }
}