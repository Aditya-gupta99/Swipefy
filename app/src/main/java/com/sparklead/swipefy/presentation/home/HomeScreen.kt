@file:OptIn(ExperimentalMaterial3Api::class)

package com.sparklead.swipefy.presentation.home

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.media3.common.MediaItem
import androidx.navigation.NavController
import com.sparklead.core.data.model.SwipeSong
import com.sparklead.core.data.model.User
import com.sparklead.swipefy.R
import com.sparklead.swipefy.presentation.components.CircularIconButton
import com.sparklead.swipefy.presentation.components.LeftAlignHeadingText
import com.sparklead.swipefy.presentation.components.LeftAlignNormalText
import com.sparklead.swipefy.presentation.components.SmallIconButton
import com.sparklead.swipefy.presentation.components.SwipeCard
import com.sparklead.swipefy.presentation.components.SwipefyLoadingProgress
import com.sparklead.swipefy.presentation.theme.Black

@Composable
fun HomeScreen(navController: NavController, padding: PaddingValues) {

    val homeViewModel: HomeViewModel = hiltViewModel()
    val state = homeViewModel.homeUiState.collectAsState().value
    val user = rememberSaveable { mutableStateOf<User?>(null) }

    var songList = rememberSaveable { listOf<SwipeSong>() }
    val currentIndex = rememberSaveable { mutableIntStateOf(0) }


    when (state) {

        is HomeUiState.Empty -> {}

        is HomeUiState.Error -> {

        }

        is HomeUiState.Loading -> {
            SwipefyLoadingProgress()
        }

        is HomeUiState.Success -> {

        }

        is HomeUiState.Ready -> {}

        is HomeUiState.RandomSongSuccess -> {
            songList = state.list
        }

        is HomeUiState.UserSuccess -> {
            user.value = state.user
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(18.dp),
        containerColor = Black
    ) {
        Column(modifier = Modifier.padding(it)) {
            Row {
                Column(modifier = Modifier.weight(2f)) {
                    LeftAlignNormalText(value = stringResource(id = R.string.welcome_back))
                    Spacer(modifier = Modifier.height(6.dp))
                    user.value?.let { it1 -> LeftAlignHeadingText(value = it1.username) }
                }
                SmallIconButton(image = Icons.Outlined.Notifications)
            }
            Spacer(modifier = Modifier.height(40.dp))
            SwipeCard(
                songList,
                currentIndex,
                onSlideChange = { homeViewModel.onUiEvents(HomeUiEvent.SeekTo(it)) },
                progress = homeViewModel.progress,
                cardSwipe = {
                    homeViewModel.onUiEvents(HomeUiEvent.PlayPause)
                    homeViewModel.currentSong = songList[currentIndex.intValue]
                    MediaItem.fromUri(Uri.parse("https://p.scdn.co/mp3-preview/7908c3512a17427dbb2747fda555aa84aedeef0d?cid=d8a5ed958d274c2e8ee717e6a4b0971d"))
                    homeViewModel.onUiEvents(HomeUiEvent.SelectedMediaChange)
                })
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                CircularIconButton(image = Icons.Filled.Close) {
                    //TODO add dislike feature
                }
                CircularIconButton(image = if(homeViewModel.isPlaying) Icons.Filled.Pause else Icons.Filled.PlayArrow) {
                    homeViewModel.onUiEvents(HomeUiEvent.PlayPause)
                    homeViewModel.currentSong = songList[currentIndex.intValue]
                    homeViewModel.onUiEvents(HomeUiEvent.SelectedMediaChange)
                }
                CircularIconButton(image = Icons.Filled.FavoriteBorder) {
                    //TODO add like feature
                }
            }
        }
    }
}