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
import com.ivtogi.zoonotlogic.navigation.Routes.OrderDetail
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
import com.ivtogi.zoonotlogic.presentation.orders.orderDetail.OrderDetailScreen
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
                navigateToSignup = {
                    navHostController.navigate(Signup.route) {
                        popUpTo(route = Login.route, popUpToBuilder = { inclusive = true })
                    }
                },
                navigateToHome = {
                    navHostController.navigate(Home.createRoute(it)) {
                        popUpTo(route = Login.route, popUpToBuilder = { inclusive = true })
                    }
                }
            )
        }

        composable(Signup.route) {
            SignupScreen(
                navigateToLogin = {
                    navHostController.navigate(Login.route) {
                        popUpTo(route = Signup.route, popUpToBuilder = { inclusive = true })
                    }
                }
            )
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
                        popUpTo(route = Admin.route, popUpToBuilder = { inclusive = true })
                    }
                },
                navigateToProduct = { userId, productId ->
                    navHostController.navigate(Product.createRoute(userId, productId)) {
                        popUpTo(route = Admin.route, popUpToBuilder = { inclusive = true })
                    }
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
                        popUpTo(route = Orders.route, popUpToBuilder = { inclusive = true })
                    }
                },
                navigateToOrderDetail = { userId, productId, goOrders ->
                    navHostController.navigate(
                        OrderDetail.createRoute(
                            userId,
                            productId,
                            goOrders
                        )
                    ) {
                        popUpTo(route = Orders.route, popUpToBuilder = { inclusive = true })
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
                        popUpTo(route = Product.route, popUpToBuilder = { inclusive = true })
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
                navigateToProfile = {
                    navHostController.navigate(Profile.createRoute(it)) {
                        popUpTo(route = Home.route, popUpToBuilder = { inclusive = true })
                    }
                },
                navigateToCart = {
                    navHostController.navigate(Cart.createRoute(it)) {
                        popUpTo(route = Home.route, popUpToBuilder = { inclusive = true })
                    }
                },
                navigateToAdmin = {
                    navHostController.navigate(Admin.createRoute(it)) {
                        popUpTo(route = Home.route, popUpToBuilder = { inclusive = true })
                    }
                },
                navigateToOrders = {
                    navHostController.navigate(Orders.createRoute(it)) {
                        popUpTo(route = Home.route, popUpToBuilder = { inclusive = true })
                    }
                },
                navigateToDetail = { userId, productId ->
                    navHostController.navigate(Detail.createRoute(userId, productId)) {
                        popUpTo(route = Home.route, popUpToBuilder = { inclusive = true })
                    }
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
                        popUpTo(route = Cart.route, popUpToBuilder = { inclusive = true })
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
                        popUpTo(route = Profile.route, popUpToBuilder = { inclusive = true })
                    }
                },
                navigateToLogin = {
                    navHostController.navigate(Login.route) {
                        popUpTo(route = Profile.route, popUpToBuilder = { inclusive = true })
                    }
                },
                navigateToOrderDetail = { userId, productId, goOrders ->
                    navHostController.navigate(
                        OrderDetail.createRoute(
                            userId,
                            productId,
                            goOrders
                        )
                    ) {
                        popUpTo(route = Profile.route, popUpToBuilder = { inclusive = true })
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
                        popUpTo(route = Detail.route, popUpToBuilder = { inclusive = true })
                    }
                }
            )
        }

        composable(
            route = OrderDetail.route,
            arguments = listOf(
                navArgument("userId") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                },
                navArgument("orderId") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                },
                navArgument("goOrders") {
                    type = NavType.BoolType
                    defaultValue = false
                }
            )
        ) {
            OrderDetailScreen(
                navigateToProfile = {
                    navHostController.navigate(Profile.createRoute(it)) {
                        popUpTo(route = OrderDetail.route, popUpToBuilder = { inclusive = true })
                    }
                },
                navigateToOrders = {
                    navHostController.navigate(Orders.createRoute(it)) {
                        popUpTo(route = OrderDetail.route, popUpToBuilder = { inclusive = true })
                    }
                }
            )
        }
    }
}
