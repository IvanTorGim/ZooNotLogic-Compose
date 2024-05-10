package com.ivtogi.zoonotlogic.presentation.admin.product.composable

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.ivtogi.zoonotlogic.R
import com.ivtogi.zoonotlogic.domain.model.Product

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductTopBar(
    userId: String,
    product: Product,
    onBackPressed: (String) -> Unit
) {
    CenterAlignedTopAppBar(
        title = { Text(text = product.name) },
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