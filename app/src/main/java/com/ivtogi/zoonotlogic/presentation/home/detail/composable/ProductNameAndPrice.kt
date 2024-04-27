package com.ivtogi.zoonotlogic.presentation.home.detail.composable

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProductNameAndPrice(name: String, price: String) {
    Text(
        text = name,
        fontSize = 18.sp,
        fontWeight = FontWeight.SemiBold
    )
    Spacer(modifier = Modifier.height(8.dp))
    Text(text = price, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
}