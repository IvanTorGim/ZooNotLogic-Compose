package com.ivtogi.zoonotlogic.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ivtogi.zoonotlogic.ui.navigation.Routes.Admin
import com.ivtogi.zoonotlogic.ui.navigation.Routes.Cart
import com.ivtogi.zoonotlogic.ui.navigation.Routes.Home
import com.ivtogi.zoonotlogic.ui.navigation.Routes.Login
import com.ivtogi.zoonotlogic.ui.navigation.Routes.Profile
import com.ivtogi.zoonotlogic.ui.navigation.Routes.Signup
import com.ivtogi.zoonotlogic.ui.login.LoginScreen
import com.ivtogi.zoonotlogic.ui.main.MainScreen
import com.ivtogi.zoonotlogic.ui.admin.AdminScreen
import com.ivtogi.zoonotlogic.ui.cart.CartScreen
import com.ivtogi.zoonotlogic.ui.home.HomeScreen
import com.ivtogi.zoonotlogic.ui.profile.ProfileScreen
import com.ivtogi.zoonotlogic.ui.singup.SignupScreen

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



