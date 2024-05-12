package com.ivtogi.zoonotlogic.presentation.composable

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.BottomAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color

@Composable
fun DefaultBottomBar(
    label: String,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    BottomAppBar(containerColor = Color.Transparent) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            DefaultButton(
                label = label,
                enabled = enabled,
                onClick = { onClick() },
            )
        }
    }
}