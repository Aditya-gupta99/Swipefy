package com.sparklead.swipefy.presentation.navigation

import com.sparklead.swipefy.R

sealed class Screen(
    val route: String,
    val title: String = "",
    val icon: Int = 0
) {

    object SignUpScreen : Screen(route = "sign_up_screen")

    object SignInScreen : Screen(route = "sign_in_screen")

    object HomeScreen : Screen(route = "home_screen", title = "Home", icon = R.drawable.ic_home)

    object SongListScreen : Screen(route = "song_list_screen", title = "Songs", icon = R.drawable.ic_song)

    object ProfileScreen : Screen(route = "profile_screen", title = "Profile", R.drawable.ic_profile)

    object DownloadSongScreen : Screen(route = "download_screen", title = "Download", R.drawable.ic_profile)
}