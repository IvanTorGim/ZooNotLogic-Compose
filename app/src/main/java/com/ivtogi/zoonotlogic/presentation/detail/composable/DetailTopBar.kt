package com.ivtogi.zoonotlogic.presentation.detail.composable

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.ivtogi.zoonotlogic.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailTopBar(
    userId: String,
    name: String,
    onBackPressed: (String) -> Unit
) {
    TopAppBar(
        title = { Text(text = name) },
        navigationIcon = {
            IconButton(
                onClick = { onBackPressed(userId) }
            ) {
                Icon(
                    imageVector = Icons.Outlined.ArrowBack,
                    contentDescription = stringResource(id = R.string.arrow_back)
                )
            }
        }
    )
}