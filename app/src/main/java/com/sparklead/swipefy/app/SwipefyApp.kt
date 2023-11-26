@file:OptIn(ExperimentalMaterial3Api::class)

package com.sparklead.swipefy.app

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.sparklead.swipefy.components.NavigationBar
import com.sparklead.swipefy.components.StatusBarColor
import com.sparklead.swipefy.navigation.Navigation
import com.sparklead.swipefy.navigation.Screen
import com.sparklead.swipefy.ui.theme.Black
import com.sparklead.swipefy.utils.Constants

@Composable
fun SwipefyApp() {

    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val route = navBackStackEntry?.destination?.route
    val isFullScreen = Constants.isFullScreen(route)

    StatusBarColor(color = Black)
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Black,
        bottomBar = {
            if (!isFullScreen) {
                Column {
                    route?.let {
                        NavigationBar(route = it) { target ->
                            navController.apply {
                                navigate(target) {
                                    restoreState = true
                                    launchSingleTop = true
                                    graph.startDestinationRoute?.let {
                                        popUpTo(route = Screen.HomeScreen.route) {
                                            saveState = true
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    ) {
        Navigation(navController = navController, padding = it)
    }
}