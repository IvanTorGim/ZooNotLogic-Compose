package com.ivtogi.zoonotlogic.presentation.home.composable

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.ivtogi.zoonotlogic.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopHomeBar(
    isAdmin: Boolean = false,
    userId: String,
    navigateToProfile: () -> Unit,
    navigateToAdmin: (String) -> Unit,
    navigateToOrders: (String) -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.zoonotlogic),
                fontWeight = FontWeight.Bold
            )
        },
        actions = {
            if (isAdmin) {
                IconButton(
                    onClick = { navigateToAdmin(userId) },
                ) {
                    Icon(
                        imageVector = Icons.Filled.Build,
                        contentDescription = stringResource(id = R.string.admin)
                    )
                }
                IconButton(
                    onClick = { navigateToOrders(userId) },
                ) {
                    Icon(
                        imageVector = Icons.Filled.ShoppingBag,
                        contentDescription = stringResource(id = R.string.orders)
                    )
                }
            }
            IconButton(
                onClick = { navigateToProfile() },
            ) {
                Icon(
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = stringResource(id = R.string.profile)
                )
            }
        }
    )
}