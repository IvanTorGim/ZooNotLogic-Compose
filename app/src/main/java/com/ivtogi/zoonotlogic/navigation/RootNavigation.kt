package com.ivtogi.zoonotlogic.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.ivtogi.zoonotlogic.navigation.Routes.Home
import com.ivtogi.zoonotlogic.navigation.Routes.Login
import com.ivtogi.zoonotlogic.navigation.Routes.Signup
import com.ivtogi.zoonotlogic.presentation.login.LoginScreen
import com.ivtogi.zoonotlogic.presentation.main.MainScreen
import com.ivtogi.zoonotlogic.presentation.signup.SignupScreen

@Composable
fun RootNavigationGraph(
    rootNavigationController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = rootNavigationController,
        route = Graph.ROOT,
        startDestination = Graph.AUTH
    ) {
        authNavigationGraph(rootNavigationController)
        composable(Graph.MAIN) {
            MainScreen()
            //TODO: NAVEGAR A LAS RUTAS DIRECTAMENTE
        }
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
                navigateToMain = { rootNavigationController.navigate(Home.route) }
            )
        }
        composable(Signup.route) {
            SignupScreen(
                navigateToLogin = { rootNavigationController.navigate(Login.route) })
        }
    }
}