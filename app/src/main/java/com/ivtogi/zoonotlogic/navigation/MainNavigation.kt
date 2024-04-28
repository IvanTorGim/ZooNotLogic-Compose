package com.ivtogi.zoonotlogic.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.ivtogi.zoonotlogic.navigation.Routes.Admin
import com.ivtogi.zoonotlogic.navigation.Routes.Cart
import com.ivtogi.zoonotlogic.navigation.Routes.Detail
import com.ivtogi.zoonotlogic.navigation.Routes.Home
import com.ivtogi.zoonotlogic.navigation.Routes.Profile
import com.ivtogi.zoonotlogic.presentation.admin.AdminScreen
import com.ivtogi.zoonotlogic.presentation.cart.CartScreen
import com.ivtogi.zoonotlogic.presentation.home.HomeScreen
import com.ivtogi.zoonotlogic.presentation.home.detail.DetailScreen
import com.ivtogi.zoonotlogic.presentation.profile.ProfileScreen


@Composable
fun MainNavigationGraph(
    mainNavigationController: NavHostController
) {
    NavHost(
        navController = mainNavigationController,
        route = Graph.MAIN,
        startDestination = Home.route
    ) {
        composable(Admin.route) {
            AdminScreen()
        }
        composable(Home.route) {
            HomeScreen(
                navigateToDetail = { mainNavigationController.navigate(Graph.DETAIL) },
            )
        }
        composable(Cart.route) { CartScreen() }
        composable(Profile.route) { ProfileScreen() }
        detailNavigationGraph(mainNavigationController)
    }
}

fun NavGraphBuilder.detailNavigationGraph(mainNavigationController: NavHostController) {
    navigation(
        route = Graph.DETAIL,
        startDestination = Detail.route
    ) {
        composable(Detail.route) {
            DetailScreen(
                onBackPressed = { mainNavigationController.popBackStack() }
            )
        }
    }
}



