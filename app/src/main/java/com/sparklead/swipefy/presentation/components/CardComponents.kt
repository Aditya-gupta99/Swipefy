package com.sparklead.swipefy.presentation.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
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
import com.sparklead.core.data.model.Song
import com.sparklead.core.data.model.SwipeSong
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
fun SwipeCard(
    songList: List<com.sparklead.core.data.model.SwipeSong>,
    currentIndex: MutableIntState,
    cardSwipe: () -> Unit,
    progress: Float,
    onSlideChange: (Float) -> Unit
) {
    val lastIndex = songList.lastIndex

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
                    cardSwipe()
                    Log.d("DIRECTION", it.name)
                    currentIndex.intValue++
                },
                onSlideChange = onSlideChange,
                progress = progress
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
fun SongCard(
    profile: com.sparklead.core.data.model.SwipeSong,
    modifier: Modifier = Modifier,
    onSlideChange: (Float) -> Unit,
    progress: Float,
    onSwipe: (Direction) -> Unit
) {
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
                model = profile.imageUrl,
                contentDescription = "poster"
            )
            Text(
                text = profile.name.replace(Regex("(\\s?-\\s?|\\s?\\(\\s?|\\s?\\[\\s?).*$"), "")
                    .trim(),
                modifier = Modifier.fillMaxWidth(),
                style = TextStyle(
                    fontSize = 24.sp,
                    fontFamily = FontFamily(Font(R.font.outfit_medium))
                ),
                color = Color.White,
                textAlign = TextAlign.Center,
                maxLines = 1
            )
            Text(
                text = profile.artist[0].name,
                modifier = Modifier.fillMaxWidth(),
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.outfit_regular))
                ),
                color = Grey,
                textAlign = TextAlign.Center
            )
            SongSlider(onSlideChange = onSlideChange, sliderPosition = progress)
        }
    }
}

@Composable
fun SwipefyRecommendedSongCard(song: Song, onSongSelected: (Song) -> Unit) {
    Card(
        modifier = Modifier.clickable(onClick = {
            onSongSelected(song)
        }),
        shape = RoundedCornerShape(4.dp),
        colors = CardDefaults.cardColors(Black)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(64.dp)
                    .padding(10.dp)
                    .weight(1f),
                model = song.imageUrl,
                contentDescription = "Song image"
            )
            Column(modifier = Modifier.weight(3f)) {
                SwipefySongHeadingTextView(
                    value = song.name.replace(
                        Regex("(\\s?-\\s?|\\s?\\(\\s?|\\s?\\[\\s?).*$"),
                        ""
                    ).trim()
                )
                SwipefySongArtistTextView(value = song.artist[0].name)
            }
            IconButton(onClick = {}) {
                AsyncImage(
                    modifier = Modifier
                        .size(22.dp)
                        .weight(.75f),
                    model = R.drawable.ic_options,
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(
                        Color.White
                    )
                )
            }
        }
    }
}

@Composable
fun SwipefyOptionsCard(leadingIcon: Int, option: String, endIcon: Int?,onSelect : ()->Unit) {

    Card(
        modifier = Modifier.clickable(onClick = {
            onSelect()
        }),
        shape = RoundedCornerShape(4.dp),
        colors = CardDefaults.cardColors(Black)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(25.dp)
                    .weight(1f),
                model = leadingIcon,
                contentDescription = "option leading image",
                colorFilter = ColorFilter.tint(
                    Color.White
                )
            )
            Column(modifier = Modifier.weight(3f)) {
                SwipefyOptionsTextView(value = option)
            }
            IconButton(onClick = {}) {
                AsyncImage(
                    modifier = Modifier
                        .size(15.dp)
                        .weight(.75f),
                    model = endIcon,
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(
                        Color.White
                    )
                )
            }
        }
    }
}