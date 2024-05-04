package com.ivtogi.zoonotlogic.presentation.detail.composable

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.BottomAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import com.ivtogi.zoonotlogic.R
import com.ivtogi.zoonotlogic.presentation.common.DefaultButton

@Composable
fun DetailBottomBar(
    enabled: Boolean,
    onClick: () -> Unit,
) {
    BottomAppBar {
        Row(verticalAlignment = Alignment.CenterVertically) {
            DefaultButton(
                label = stringResource(id = R.string.add_cart),
                enabled = enabled,
                onClick = { onClick() },
            )
        }
    }
}