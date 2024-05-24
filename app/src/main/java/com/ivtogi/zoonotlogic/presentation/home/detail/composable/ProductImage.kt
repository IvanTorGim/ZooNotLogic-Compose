package com.ivtogi.zoonotlogic.presentation.home.detail.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ivtogi.zoonotlogic.presentation.home.detail.DetailViewModel

@Composable
fun ProductImage(
    image: String,
    viewModel: DetailViewModel
) {
    Box {
        AsyncImage(
            model = image,
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        )
        IconButton(
            onClick = { viewModel.previousImage() },
            modifier = Modifier.align(androidx.compose.ui.Alignment.CenterStart)
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBackIos,
                contentDescription = "Arrow left",
                tint = Color.Black
            )
        }
        IconButton(
            onClick = { viewModel.nextImage() },
            modifier = Modifier.align(androidx.compose.ui.Alignment.CenterEnd)
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowForwardIos,
                contentDescription = "Arrow right",
                tint = Color.Black
            )
        }

    }
}