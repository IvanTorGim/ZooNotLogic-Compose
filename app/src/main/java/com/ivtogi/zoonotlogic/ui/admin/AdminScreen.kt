package com.ivtogi.zoonotlogic.ui.admin

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.ivtogi.zoonotlogic.R

@Composable
fun AdminScreen() {
    Box {
        Text(text = stringResource(id = R.string.admin))
    }
}