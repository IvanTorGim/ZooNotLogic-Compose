package com.ivtogi.zoonotlogic.navigation

sealed class Routes(val route: String) {
    data object Login : Routes("login")
    data object Signup : Routes("signup")
    data object Admin : Routes("admin")
    data object Home : Routes("home/?userId={userId}") {
        fun createRoute(userId: String) = "home/?userId=$userId"
    }
    data object Cart : Routes("cart")
    data object Profile : Routes("profile")
    data object Detail : Routes("detail/?productId={productId}") {
        fun createRoute(productId: String) = "detail/?productId=$productId"
    }
}