@file:OptIn(ExperimentalMaterial3Api::class)

package com.sparklead.swipefy.presentation.song_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.sparklead.core.data.model.Artist
import com.sparklead.core.data.model.Song
import com.sparklead.swipefy.presentation.components.HeadingTextView
import com.sparklead.swipefy.presentation.components.SwipefyLoadingProgress
import com.sparklead.swipefy.presentation.components.SwipefyMiniPlayer
import com.sparklead.swipefy.presentation.components.SwipefyPagingAppendProgress
import com.sparklead.swipefy.presentation.components.SwipefyRecommendedSongCard
import com.sparklead.swipefy.presentation.components.SwipefySweetError
import com.sparklead.swipefy.presentation.theme.Black

@Composable
fun SongListScreen(
    navController: NavController,
    padding: PaddingValues,
    startMusicService: () -> Unit
) {

    val songListViewModel: SongListViewModel = hiltViewModel()
    val songPagingItems = songListViewModel.songListUiState.collectAsLazyPagingItems()
    val songIndex = rememberSaveable { mutableStateOf<Int?>(null) }
    val miniPlayerSong = rememberSaveable {
        mutableStateOf(
            Song(
                id = "3yHyiUDJdz02FZ6jfUbsmY",
                name = "Satranga",
                duration = 300,
                previewUrl = "https://p.scdn.co/mp3-preview/7908c3512a17427dbb2747fda555aa84aedeef0d?cid=d8a5ed958d274c2e8ee717e6a4b0971d",
                imageUrl = "https://i.scdn.co/image/ab67616d0000b273021d7017f73387b008eab271",
                artist = listOf(
                    Artist(
                        id = "4YRxDV8wJFPHPTeXepOstw",
                        name = "Arijit Singh"
                    )
                )
            )
        )
    }



    Scaffold(
        modifier = Modifier.padding(padding),
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = Black),
                title = { HeadingTextView(value = "Recommended Song") }
            )
        },
        bottomBar = {
            SwipefyMiniPlayer(
                miniPlayerSong.value,
                download = { songListViewModel.downloadSong(it) },
                play = {
                    songIndex.value?.let { songListViewModel.onPlayMusic(it) }
                    startMusicService()
                }
            )
        },
        containerColor = Black
    ) {

        when (songPagingItems.loadState.refresh) {
            is LoadState.Error -> {
                SwipefySweetError(message = "Failed to Fetch Song") {
                    songListViewModel.getFavoriteArtist()
                }
            }

            LoadState.Loading -> {
                SwipefyLoadingProgress()
            }

            is LoadState.NotLoading -> Unit
        }

        Column(
            modifier = Modifier.padding(it)
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                items(songPagingItems.itemCount) { index ->
                    songPagingItems[index]?.let { it1 ->
                        SwipefyRecommendedSongCard(song = it1) { song ->
//                            songIndex.value = index
                            miniPlayerSong.value = song
                        }
                    }
                }

                when (songPagingItems.loadState.append) {
                    is LoadState.Error -> Unit

                    LoadState.Loading -> {
                        item {
                            SwipefyPagingAppendProgress()
                        }
                    }

                    is LoadState.NotLoading -> Unit
                }
            }
        }
    }
}