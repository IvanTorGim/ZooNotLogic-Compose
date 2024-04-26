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


@Composable
fun MainScreen(
    rootNavHostController: NavHostController,
    viewModel: MainViewModel = hiltViewModel()
) {
    val mainNavigationController: NavHostController = rememberNavController()
    Scaffold(bottomBar = {
        NavigationBottomBar(mainNavigationController, viewModel.getNavBarItems())
    }) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            MainNavigationGraph(rootNavHostController, mainNavigationController)
        }
    }
}


