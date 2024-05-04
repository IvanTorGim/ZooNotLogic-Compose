package com.ivtogi.zoonotlogic.presentation.detail.composable

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProductPrice(price: String) {
    Row {
        Spacer(modifier = Modifier.weight(1f))
        Text(text = price, fontSize = 28.sp, fontWeight = FontWeight.SemiBold)
        Spacer(modifier = Modifier.width(16.dp))
    }
}