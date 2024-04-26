package com.ivtogi.zoonotlogic.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.ivtogi.zoonotlogic.navigation.Routes.Admin
import com.ivtogi.zoonotlogic.navigation.Routes.Cart
import com.ivtogi.zoonotlogic.navigation.Routes.Detail
import com.ivtogi.zoonotlogic.navigation.Routes.Home
import com.ivtogi.zoonotlogic.navigation.Routes.Login
import com.ivtogi.zoonotlogic.navigation.Routes.Profile
import com.ivtogi.zoonotlogic.navigation.Routes.Signup
import com.ivtogi.zoonotlogic.presentation.admin.AdminScreen
import com.ivtogi.zoonotlogic.presentation.cart.CartScreen
import com.ivtogi.zoonotlogic.presentation.home.HomeScreen
import com.ivtogi.zoonotlogic.presentation.home.detail.DetailScreen
import com.ivtogi.zoonotlogic.presentation.login.LoginScreen
import com.ivtogi.zoonotlogic.presentation.main.MainScreen
import com.ivtogi.zoonotlogic.presentation.profile.ProfileScreen
import com.ivtogi.zoonotlogic.presentation.signup.SignupScreen

@Composable
fun RootNavigationGraph(
    rootNavigationController: NavHostController
) {
    NavHost(
        navController = rootNavigationController,
        route = Graph.ROOT,
        startDestination = Graph.AUTH
    ) {
        authNavigationGraph(rootNavigationController)
        composable(Graph.MAIN) {
            MainScreen(rootNavigationController)
        }
        detailNavigationGraph()
    }
}

fun NavGraphBuilder.authNavigationGraph(
    rootNavigationController: NavHostController
) {
    navigation(
        route = Graph.AUTH,
        startDestination = Login.route
    ) {
        composable(Login.route) {
            LoginScreen(
                navigateToSignup = { rootNavigationController.navigate(Signup.route) },
                navigateToMain = { rootNavigationController.navigate(Graph.MAIN) }
            )
        }
        composable(Signup.route) {
            SignupScreen(
                navigateToLogin = { rootNavigationController.navigate(Login.route) })
        }
    }
}

@Composable
fun MainNavigationGraph(
    rootNavigationController: NavHostController,
    mainNavigationController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = mainNavigationController,
        route = Graph.MAIN,
        startDestination = Home.route
    ) {
        composable(Admin.route) { AdminScreen() }
        composable(Home.route) {
            HomeScreen(navigateToDetail = { rootNavigationController.navigate(Graph.DETAIL) })
        }
        composable(Cart.route) { CartScreen() }
        composable(Profile.route) { ProfileScreen() }

    }
}

fun NavGraphBuilder.detailNavigationGraph() {
    navigation(
        route = Graph.DETAIL,
        startDestination = Detail.route
    ) {
        composable(Detail.route) { DetailScreen() }
    }
}



