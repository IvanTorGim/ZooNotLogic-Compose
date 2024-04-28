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
import com.ivtogi.zoonotlogic.navigation.Routes.Profile
import com.ivtogi.zoonotlogic.navigation.Routes.Signup
import com.ivtogi.zoonotlogic.presentation.admin.AdminScreen
import com.ivtogi.zoonotlogic.presentation.cart.CartScreen
import com.ivtogi.zoonotlogic.presentation.detail.DetailScreen
import com.ivtogi.zoonotlogic.presentation.home.HomeScreen
import com.ivtogi.zoonotlogic.presentation.login.LoginScreen
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
        composable(Admin.route) {
            AdminScreen()
        }
        composable(
            route = Home.route,
            arguments = listOf(
                navArgument("habitId") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                })
        ) {
            HomeScreen(
                navigateToDetail = { navHostController.navigate(Detail.route) },
                navigateToProfile = { navHostController.navigate(Profile.route) },
                navigateToAdmin = { navHostController.navigate(Admin.route) }
            )
        }
        composable(Cart.route) { CartScreen() }
        composable(Profile.route) { ProfileScreen() }
        composable(Detail.route) {
            DetailScreen(
                onBackPressed = { navHostController.popBackStack() }
            )
        }
    }
}
