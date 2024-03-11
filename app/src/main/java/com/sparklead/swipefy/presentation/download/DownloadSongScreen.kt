@file:OptIn(ExperimentalMaterial3Api::class)

package com.sparklead.swipefy.presentation.download

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Sort
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.sparklead.swipefy.R
import com.sparklead.swipefy.presentation.components.SwipefyRecommendedSongCard
import com.sparklead.swipefy.presentation.theme.Black
import com.sparklead.swipefy.presentation.theme.DarkGreen

@Composable
fun DownloadSongScreen(navController: NavController, paddingValues: PaddingValues) {

    val downloadViewModel: DownloadSongViewModel = hiltViewModel()
    val state = downloadViewModel.downloadSongUiState.collectAsState().value

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = Black),
                title = {
                    Text(
                        text = "Downloads",
                        style = TextStyle(
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Medium,
                            fontStyle = FontStyle.Normal,
                            fontFamily = FontFamily(Font(R.font.outfit_regular))
                        ),
                        color = DarkGreen,
                        textAlign = TextAlign.Start
                    )
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(imageVector = Icons.Outlined.Sort, contentDescription = null, tint = DarkGreen)
                    }
                }
            )
        },
        containerColor = Black
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {

            when (state) {
                is DownloadSongUiState.Error -> {

                }

                is DownloadSongUiState.Loading -> {

                }

                is DownloadSongUiState.Success -> {
                    LazyColumn {
                        items(state.list) { song ->
                            SwipefyRecommendedSongCard(song = song) {

                            }
                        }
                    }
                }
            }
        }
    }
}