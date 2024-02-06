@file:OptIn(ExperimentalMaterial3Api::class)

package com.sparklead.swipefy.presentation.song_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.sparklead.swipefy.domain.model.SwipeSong
import com.sparklead.swipefy.presentation.components.HeadingTextView
import com.sparklead.swipefy.presentation.components.SwipefyRecommendedSongCard
import com.sparklead.swipefy.presentation.home.HomeUiState
import com.sparklead.swipefy.presentation.home.HomeViewModel
import com.sparklead.swipefy.presentation.theme.Black

@Composable
fun SongListScreen(navController: NavController, padding: PaddingValues) {

    val homeViewModel: HomeViewModel = hiltViewModel()
    val state = homeViewModel.homeUiState.collectAsState().value

    var songList = rememberSaveable { listOf<SwipeSong>() }

    when (state) {

        is HomeUiState.Empty -> {}

        is HomeUiState.Error -> {

        }

        is HomeUiState.Loading -> {}

        is HomeUiState.Success -> {

        }

        is HomeUiState.Ready -> {}

        is HomeUiState.RandomSongSuccess -> {
            songList = state.list
        }
    }

    Scaffold(
        modifier = Modifier.padding(padding),
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = Black),
                title = { HeadingTextView(value = "Recommended Song") }
            )
        },
        containerColor = Black
    ) {
        Column(
            modifier = Modifier.padding(it)
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            ) {
                items(songList) { song ->
                    SwipefyRecommendedSongCard(song = song)
                }
            }
        }
    }
}