package com.ivtogi.zoonotlogic.presentation.admin.product.composable

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Label(text: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Divider(modifier = Modifier.weight(1f))
        Spacer(modifier = Modifier.width(8.dp))
        Text(text)
        Spacer(modifier = Modifier.width(8.dp))
        Divider(modifier = Modifier.weight(1f))
        Spacer(modifier = Modifier.height(16.dp))
    }
}