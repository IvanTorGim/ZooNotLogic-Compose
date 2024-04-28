package com.ivtogi.zoonotlogic.presentation.home.composable

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Build
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.ivtogi.zoonotlogic.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopHomeBar(
    isAdmin: Boolean = false,
    navigateToProfile: () -> Unit,
    navigateToAdmin: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.zoonotlogic),
                fontWeight = FontWeight.Bold
            )
        },
        actions = {
            if (isAdmin) {
                IconButton(
                    onClick = { navigateToAdmin() },
                ) {
                    Icon(
                        imageVector = Icons.Filled.Build,
                        contentDescription = stringResource(id = R.string.admin)
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