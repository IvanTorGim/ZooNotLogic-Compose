package com.ivtogi.zoonotlogic.presentation.cart

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.ivtogi.zoonotlogic.R

@Composable
fun CartScreen() {
    Text(text = stringResource(id = R.string.cart))
}