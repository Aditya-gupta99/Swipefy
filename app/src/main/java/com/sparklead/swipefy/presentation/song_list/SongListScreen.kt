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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.sparklead.swipefy.presentation.components.HeadingTextView
import com.sparklead.swipefy.presentation.components.SwipefyRecommendedSongCard
import com.sparklead.swipefy.presentation.theme.Black

@Composable
fun SongListScreen(navController: NavController, padding: PaddingValues) {

    val songListViewModel: SongListViewModel = hiltViewModel()
    val songPagingItems = songListViewModel.songListUiState.collectAsLazyPagingItems()

//    var songList = rememberSaveable { listOf<SwipeSong>() }


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
                items(songPagingItems.itemCount) { index ->
                    songPagingItems[index]?.let { it1 -> SwipefyRecommendedSongCard(song = it1) }
                }

                when (songPagingItems.loadState.append) {
                    is LoadState.Error -> Unit

                    LoadState.Loading -> {

                    }

                    is LoadState.NotLoading -> Unit
                }
                when (songPagingItems.loadState.prepend) {
                    is LoadState.Error -> {
                    }

                    LoadState.Loading -> {

                    }

                    is LoadState.NotLoading -> Unit
                }
            }
        }
    }
}