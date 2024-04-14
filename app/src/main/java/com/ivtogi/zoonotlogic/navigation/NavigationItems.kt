package com.ivtogi.zoonotlogic.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Build
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import com.ivtogi.zoonotlogic.R

sealed class NavigationItems(
    val label: Int,
    val iconSelected: ImageVector,
    val iconUnselected: ImageVector,
    val route: String
) {
    data object Admin : NavigationItems(
        label = R.string.admin,
        iconSelected = Icons.Filled.Build,
        iconUnselected = Icons.Outlined.Build,
        route = Routes.Admin.route
    )

    data object Home : NavigationItems(
        label = R.string.home,
        iconSelected = Icons.Filled.Home,
        iconUnselected = Icons.Outlined.Home,
        route = Routes.Home.route
    )

    data object Cart : NavigationItems(
        label = R.string.cart,
        iconSelected = Icons.Filled.ShoppingCart,
        iconUnselected = Icons.Outlined.ShoppingCart,
        route = Routes.Cart.route
    )

    data object Profile : NavigationItems(
        label = R.string.profile,
        iconSelected = Icons.Filled.Person,
        iconUnselected = Icons.Outlined.Person,
        route = Routes.Profile.route
    )
}

