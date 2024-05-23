package com.ivtogi.zoonotlogic.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ivtogi.zoonotlogic.navigation.Routes.Admin
import com.ivtogi.zoonotlogic.navigation.Routes.Cart
import com.ivtogi.zoonotlogic.navigation.Routes.Detail
import com.ivtogi.zoonotlogic.navigation.Routes.Home
import com.ivtogi.zoonotlogic.navigation.Routes.Login
import com.ivtogi.zoonotlogic.navigation.Routes.Orders
import com.ivtogi.zoonotlogic.navigation.Routes.Product
import com.ivtogi.zoonotlogic.navigation.Routes.Profile
import com.ivtogi.zoonotlogic.navigation.Routes.Signup
import com.ivtogi.zoonotlogic.presentation.admin.AdminScreen
import com.ivtogi.zoonotlogic.presentation.admin.product.ProductScreen
import com.ivtogi.zoonotlogic.presentation.cart.CartScreen
import com.ivtogi.zoonotlogic.presentation.home.HomeScreen
import com.ivtogi.zoonotlogic.presentation.home.detail.DetailScreen
import com.ivtogi.zoonotlogic.presentation.login.LoginScreen
import com.ivtogi.zoonotlogic.presentation.orders.OrdersScreen
import com.ivtogi.zoonotlogic.presentation.profile.ProfileScreen
import com.ivtogi.zoonotlogic.presentation.signup.SignupScreen

@Composable
fun NavigationGraph(
    navHostController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navHostController,
        startDestination = Login.route
    ) {
        composable(Login.route) {
            LoginScreen(
                navigateToSignup = { navHostController.navigate(Signup.route) },
                navigateToHome = { navHostController.navigate(Home.createRoute(it)) }
            )
        }

        composable(Signup.route) {
            SignupScreen(
                navigateToLogin = { navHostController.navigate(Login.route) })
        }

        composable(
            route = Admin.route,
            arguments = listOf(
                navArgument("userId") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                })
        ) {
            AdminScreen(
                navigateToHome = {
                    navHostController.navigate(Home.createRoute(it)) {
                        popUpTo(Login.route)
                    }
                },
                navigateToProduct = { userId, productId ->
                    navHostController.navigate(Product.createRoute(userId, productId))
                }
            )
        }

        composable(
            route = Orders.route,
            arguments = listOf(
                navArgument("userId") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                })
        ) {
            OrdersScreen(
                navigateToHome = {
                    navHostController.navigate(Home.createRoute(it)) {
                        popUpTo(Home.route)
                    }
                }
            )
        }

        composable(
            route = Product.route,
            arguments = listOf(
                navArgument("userId") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                },
                navArgument("productId") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                }
            )
        ) {
            ProductScreen(
                navigateToAdmin = {
                    navHostController.navigate(Admin.createRoute(it)) {
                        popUpTo(Home.route)
                    }
                }
            )
        }

        composable(
            route = Home.route,
            arguments = listOf(
                navArgument("userId") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                })
        ) {
            HomeScreen(
                navigateToProfile = { navHostController.navigate(Profile.createRoute(it)) },
                navigateToCart = { navHostController.navigate(Cart.createRoute(it)) },
                navigateToAdmin = { navHostController.navigate(Admin.createRoute(it)) },
                navigateToOrders = { navHostController.navigate(Orders.createRoute(it)) },
                navigateToDetail = { userId, productId ->
                    navHostController.navigate(Detail.createRoute(userId, productId))
                }
            )
        }

        composable(
            route = Cart.route,
            arguments = listOf(
                navArgument("userId") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                })
        ) {
            CartScreen(
                navigateToHome = {
                    navHostController.navigate(Home.createRoute(it)) {
                        popUpTo(Login.route)
                    }
                }
            )
        }

        composable(
            route = Profile.route,
            arguments = listOf(
                navArgument("userId") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                })
        ) {
            ProfileScreen(
                navigateToHome = {
                    navHostController.navigate(Home.createRoute(it)) {
                        popUpTo(Login.route)
                    }
                }
            )
        }

        composable(
            route = Detail.route,
            arguments = listOf(
                navArgument("userId") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                },
                navArgument("productId") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                }
            )
        ) {
            DetailScreen(
                navigateToHome = {
                    navHostController.navigate(Home.createRoute(it)) {
                        popUpTo(Login.route)
                    }
                }
            )
        }
    }
}
