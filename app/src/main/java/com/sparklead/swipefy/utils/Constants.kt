package com.sparklead.swipefy.utils

object Constants {

    private val fullScreenRoutes = listOf(
        "sign_up_screen",
        "sign_in_screen"
    )

    fun isFullScreen(route: String?): Boolean {
        return fullScreenRoutes.contains(route)
    }
}