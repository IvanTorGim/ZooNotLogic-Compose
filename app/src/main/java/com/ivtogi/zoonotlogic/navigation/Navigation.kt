package com.ivtogi.zoonotlogic.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ivtogi.zoonotlogic.navigation.Routes.Admin
import com.ivtogi.zoonotlogic.navigation.Routes.Cart
import com.ivtogi.zoonotlogic.navigation.Routes.Home
import com.ivtogi.zoonotlogic.navigation.Routes.Login
import com.ivtogi.zoonotlogic.navigation.Routes.Profile
import com.ivtogi.zoonotlogic.navigation.Routes.Signup
import com.ivtogi.zoonotlogic.presentation.login.LoginScreen
import com.ivtogi.zoonotlogic.presentation.main.MainScreen
import com.ivtogi.zoonotlogic.presentation.admin.AdminScreen
import com.ivtogi.zoonotlogic.presentation.cart.CartScreen
import com.ivtogi.zoonotlogic.presentation.home.HomeScreen
import com.ivtogi.zoonotlogic.presentation.profile.ProfileScreen
import com.ivtogi.zoonotlogic.presentation.signup.SignupScreen

@Composable
fun AuthNavigationGraph(
    navigationController: NavHostController
) {
    NavHost(
        navController = navigationController,
        route = Graph.AUTH,
        startDestination = Login.route
    ) {
        composable(Login.route) {
            LoginScreen(
                navigateToSignup = { navigationController.navigate(Signup.route) },
                navigateToMain = { navigationController.navigate(Graph.MAIN) }
            )
        }
        composable(Signup.route) {
            SignupScreen(
                navigationToLogin = { navigationController.navigate(Login.route) })
        }
        composable(route = Graph.MAIN) {
            MainScreen()
        }
    }
}

@Composable
fun MainNavigationGraph(navigationController: NavHostController) {
    NavHost(
        navController = navigationController,
        route = Graph.MAIN,
        startDestination = Home.route
    ) {
        composable(Admin.route) { AdminScreen() }
        composable(Home.route) { HomeScreen() }
        composable(Cart.route) { CartScreen() }
        composable(Profile.route) { ProfileScreen() }
    }
}



