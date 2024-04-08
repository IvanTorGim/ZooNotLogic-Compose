package com.ivtogi.zoonotlogic.ui.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ivtogi.zoonotlogic.R
import com.ivtogi.zoonotlogic.ui.navigation.MainNavigationGraph


@Composable
fun MainScreen(viewModel: MainViewModel = hiltViewModel()) {
    val navigationController: NavHostController = rememberNavController()
    Scaffold(bottomBar = {
        ZnlNavigationBottomBar(navigationController, viewModel)
    }) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            MainNavigationGraph(navigationController)
        }
    }
}


@Composable
fun ZnlNavigationBottomBar(navHostController: NavHostController, viewModel: MainViewModel) {

    val items = viewModel.getNavBarItems()

    // Para obtener la ruta en la que estamos
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar(
        containerColor = colorResource(id = R.color.primary),
        contentColor = Color.Black,
    ) {
        items.forEach { item ->
            val selected = currentRoute == item.route
            NavigationBarItem(
                label = { Text(text = stringResource(id = item.label)) },
                selected = selected,
                onClick = {
                    navHostController.navigate(item.route) {
                        // Siempre que pulsemos el boton atras nos llevara a la home
                        popUpTo(navHostController.graph.startDestinationId)
                        // Solo hay una instancia de cada screen en el backstack
                        launchSingleTop = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = if (selected) item.iconSelected else item.iconUnselected,
                        contentDescription = stringResource(id = item.label)
                    )
                },
                colors = NavigationBarItemDefaults.colors(indicatorColor = colorResource(id = R.color.primary))
            )
        }
    }
}