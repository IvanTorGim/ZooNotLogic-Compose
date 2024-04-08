package com.ivtogi.zoonotlogic.ui.navigation

sealed class Routes(val route: String) {
    // Auth routes
    data object Login : Routes("login")
    data object Signup : Routes("signup")

    // Main routes
    data object Admin : Routes("admin")
    data object Home : Routes("home")
    data object Cart : Routes("cart")
    data object Profile : Routes("profile")
}