package com.sparklead.swipefy.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.RememberObserver
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.sparklead.swipefy.R
import com.sparklead.swipefy.data.dto.track.TrackDto
import com.sparklead.swipefy.presentation.components.CircularIconButton
import com.sparklead.swipefy.presentation.components.LeftAlignHeadingText
import com.sparklead.swipefy.presentation.components.LeftAlignNormalText
import com.sparklead.swipefy.presentation.components.SmallIconButton
import com.sparklead.swipefy.presentation.components.SwipeCard
import com.sparklead.swipefy.presentation.theme.Black
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun HomeScreen(navController: NavController) {

    val homeViewModel: HomeViewModel = hiltViewModel()
    val state = homeViewModel.homeUiState.collectAsState().value

    val idList = remember { listOf("4blqlsA1uf2d2I40E90EUC", "2JzZzZUQj3Qff7wapcbKjc", "3yHyiUDJdz02FZ6jfUbsmY") }
    val songList = remember { mutableStateListOf<TrackDto>() }


    LaunchedEffect(key1 = true) {
        idList.forEach {
            homeViewModel.getTrackById(it)
        }
    }

    when (state) {

        is HomeUiState.Empty -> {}

        is HomeUiState.Error -> {

        }

        is HomeUiState.Loading -> {}
        
        is HomeUiState.Success -> {
            state.trackDto?.let { songList.add(it) }
        }
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(18.dp),
        color = Black
    ) {
        Column {
            Row {
                Column(modifier = Modifier.weight(2f)) {
                    LeftAlignNormalText(value = stringResource(id = R.string.welcome_back))
                    Spacer(modifier = Modifier.height(6.dp))
                    LeftAlignHeadingText(value = "Ankur Singh")
                }
                SmallIconButton(image = Icons.Outlined.Notifications)
            }
            Spacer(modifier = Modifier.height(40.dp))
            SwipeCard(songList)
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                CircularIconButton(image = Icons.Filled.Close)
                CircularIconButton(image = Icons.Filled.Pause)
                CircularIconButton(image = Icons.Filled.FavoriteBorder)
            }
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(navController = rememberNavController())
}