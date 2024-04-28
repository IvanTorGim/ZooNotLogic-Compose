package com.ivtogi.zoonotlogic.presentation.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ivtogi.zoonotlogic.navigation.MainNavigationGraph
import com.ivtogi.zoonotlogic.navigation.NavigationBottomBar
import com.ivtogi.zoonotlogic.navigation.NavigationItems


@Composable
fun MainScreen(
    mainNavigationController: NavHostController = rememberNavController(),
    viewModel: MainViewModel = hiltViewModel(),
) {

    val items = listOf(
        NavigationItems.Admin,
        NavigationItems.Home,
        NavigationItems.Cart,
        NavigationItems.Profile
    )
    Scaffold(bottomBar = {
        //TODO: no funciona bien la bottom bar, no pinta admin si eres admin
        NavigationBottomBar(mainNavigationController, items)
    }) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            MainNavigationGraph(mainNavigationController)
        }
    }
}


