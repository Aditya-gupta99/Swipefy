@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.sparklead.swipefy.presentation.profile

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.sparklead.swipefy.R
import com.sparklead.swipefy.presentation.components.ProfileTextField
import com.sparklead.swipefy.presentation.components.SwipefyOptionsCard
import com.sparklead.swipefy.presentation.navigation.Screen

@Composable
fun ProfileScreen(navController: NavController, padding: PaddingValues) {

    val profileViewModel: ProfileViewModel = hiltViewModel()
    val imageUri = rememberSaveable { mutableStateOf("") }
    val state = profileViewModel.profileUiState.collectAsStateWithLifecycle().value


    when (state) {
        ProfileUiState.Loading -> {

        }
    }

    val painter = rememberAsyncImagePainter(
        if (imageUri.value.isEmpty())
            R.drawable.profile_icon
        else
            imageUri.value
    )
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let { imageUri.value = it.toString() }
    }

    StatusBarColor()

    Scaffold(modifier = Modifier.padding(padding)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(it)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .height(255.dp)
                    .fillMaxWidth()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color(0xFF1DB954),
                                Color.Black.copy(0.75f)
                            )
                        )
                    )
            ) {
                Card(
                    shape = CircleShape,
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .size(130.dp)
                ) {
                    Image(
                        painter = painter,
                        contentDescription = null,
                        modifier = Modifier
                            .wrapContentSize()
                            .background(Color.Black)
                            .clickable { launcher.launch("image/*") },
                        contentScale = ContentScale.Crop
                    )
                }
                Text(
                    modifier = Modifier.padding(start = 16.dp),
                    text = "Ankur Singh",
                    style = TextStyle(
                        fontSize = 28.sp,
                        fontFamily = FontFamily(Font(R.font.outfit_medium))
                    ),
                    color = Color.White,
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp)
            ) {
                ProfileTextField(footerText = R.string.swipe_stats)
            }

            Spacer(Modifier.height(8.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            )
            {
                ProfileTextField(headerText = "38", footerText = R.string.swipes)
                ProfileTextField(headerText = "22", footerText = R.string.liked)
                ProfileTextField(headerText = "16", footerText = R.string.disliked)
                ProfileTextField(headerText = "0", footerText = R.string.skips)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp)
            ) {
                ProfileTextField(footerText = R.string.swipe_activity)
            }
            Spacer(modifier = Modifier.height(40.dp))
            SwipefyOptionsCard(
                leadingIcon = R.drawable.download,
                option = "Download",
                endIcon = R.drawable.right_icon
            ) {
                navController.navigate(Screen.DownloadSongScreen.route)
            }
            SwipefyOptionsCard(
                leadingIcon = R.drawable.edit_icon,
                option = "Edit Profile",
                endIcon = R.drawable.right_icon
            ) {

            }
            SwipefyOptionsCard(
                leadingIcon = R.drawable.log_out,
                option = "Log Out",
                endIcon = null
            ) {
                profileViewModel.logout().also {
                    navController.navigate(Screen.SignInScreen.route) {
                        popUpTo(Screen.HomeScreen.route) {
                            inclusive = true
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun StatusBarColor() {
    val systemUiController = rememberSystemUiController()
    val statusBarColor = Color(0xFF1DB954)

    DisposableEffect(key1 = true) {
        systemUiController.setStatusBarColor(statusBarColor)
        onDispose {
            systemUiController.setStatusBarColor(
                color = Color.Black,
                darkIcons = false
            )
        }
    }
}