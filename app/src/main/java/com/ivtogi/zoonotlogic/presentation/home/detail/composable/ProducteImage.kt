package com.ivtogi.zoonotlogic.presentation.home.detail.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import com.ivtogi.zoonotlogic.R

@Composable
fun ProductImage(
    image: String,
) {
    Box {
        AsyncImage(
            model = image,
            contentDescription = "",
            placeholder = painterResource(id = R.drawable.butterfly),
            modifier = Modifier.fillMaxWidth()
        )

    }
}