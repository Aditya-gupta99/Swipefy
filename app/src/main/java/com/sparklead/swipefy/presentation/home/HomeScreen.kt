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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.sparklead.swipefy.R
import com.sparklead.swipefy.presentation.components.CircularIconButton
import com.sparklead.swipefy.presentation.components.LeftAlignHeadingText
import com.sparklead.swipefy.presentation.components.LeftAlignNormalText
import com.sparklead.swipefy.presentation.components.SmallIconButton
import com.sparklead.swipefy.presentation.components.SwipeCard
import com.sparklead.swipefy.presentation.theme.Black

@Composable
fun HomeScreen(navController: NavController) {
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
            SwipeCard()
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