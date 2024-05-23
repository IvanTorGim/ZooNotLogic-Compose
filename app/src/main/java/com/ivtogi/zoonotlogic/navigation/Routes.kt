package com.ivtogi.zoonotlogic.navigation

sealed class Routes(val route: String) {
    data object Login : Routes("login")
    data object Signup : Routes("signup")
    data object Admin : Routes("admin/?userId={userId}") {
        fun createRoute(userId: String) = "admin/?userId=$userId"
    }
    data object Orders : Routes("orders/?userId={userId}") {
        fun createRoute(userId: String) = "orders/?userId=$userId"
    }

    data object Product : Routes("product/?userId={userId}&productId={productId}") {
        fun createRoute(userId: String, productId: String?) =
            "product/?userId=$userId&productId=$productId"
    }

    data object Home : Routes("home/?userId={userId}") {
        fun createRoute(userId: String) = "home/?userId=$userId"
    }

    data object Cart : Routes("cart/?userId={userId}") {
        fun createRoute(userId: String) = "cart/?userId=$userId"
    }

    data object Profile : Routes("profile/?userId={userId}") {
        fun createRoute(userId: String) = "profile/?userId=$userId"
    }
    data object Detail : Routes("detail/?userId={userId}&productId={productId}") {
        fun createRoute(userId: String, productId: String) =
            "detail/?userId=$userId&productId=$productId"
    }
}