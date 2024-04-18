package com.sparklead.swipefy.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sparklead.swipefy.presentation.download.DownloadSongScreen
import com.sparklead.swipefy.presentation.home.HomeScreen
import com.sparklead.swipefy.presentation.profile.ProfileScreen
import com.sparklead.swipefy.presentation.sign_in.SignInScreen
import com.sparklead.swipefy.presentation.sign_up.SignUpScreen
import com.sparklead.swipefy.presentation.song_list.SongListScreen

@Composable
fun Navigation(navController: NavHostController, padding: PaddingValues,startMusicService: ()->Unit) {

    NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
        composable(Screen.SignUpScreen.route) {
            SignUpScreen(navController)
        }
        composable(Screen.SignInScreen.route) {
            SignInScreen(navController)
        }
        composable(Screen.HomeScreen.route) {
            HomeScreen(navController,padding)
        }
        composable(Screen.SongListScreen.route) {
            SongListScreen(navController,padding) {
                startMusicService()
            }
        }
        composable(Screen.ProfileScreen.route) {
            ProfileScreen(navController,padding)
        }
        composable(Screen.DownloadSongScreen.route) {
            DownloadSongScreen(navController,padding)
        }
    }
}