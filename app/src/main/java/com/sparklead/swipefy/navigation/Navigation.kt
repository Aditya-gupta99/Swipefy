package com.sparklead.swipefy.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sparklead.swipefy.screens.HomeScreen
import com.sparklead.swipefy.screens.ProfileScreen
import com.sparklead.swipefy.screens.SignInScreen
import com.sparklead.swipefy.screens.SignUpScreen
import com.sparklead.swipefy.screens.SongListScreen

@Composable
fun Navigation(navController: NavHostController, padding: PaddingValues) {

    NavHost(navController = navController, startDestination = Screen.SignInScreen.route) {
        composable(Screen.SignUpScreen.route) {
            SignUpScreen(navController)
        }
        composable(Screen.SignInScreen.route) {
            SignInScreen(navController)
        }
        composable(Screen.HomeScreen.route) {
            HomeScreen(navController)
        }
        composable(Screen.SongListScreen.route) {
            SongListScreen(navController)
        }
        composable(Screen.ProfileScreen.route) {
            ProfileScreen(navController)
        }
    }
}