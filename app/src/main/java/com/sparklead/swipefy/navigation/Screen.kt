package com.sparklead.swipefy.navigation

sealed class Screen(val route : String) {

    object SignUpScreen : Screen("sign_up_screen")

    object SignInScreen : Screen("sign_in_screen")
}