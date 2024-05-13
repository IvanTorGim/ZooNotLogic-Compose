package com.ivtogi.zoonotlogic.presentation.home.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ivtogi.zoonotlogic.domain.model.Product

@Composable
fun NewCollectionProduct(
    product: Product,
    userId: String,
    navigateToDetail: (String, String) -> Unit
) {
    Card(
        elevation = CardDefaults.elevatedCardElevation(2.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .fillMaxHeight()
            .width(320.dp)
            .padding(8.dp)
            .clickable { navigateToDetail(userId, product.id) }
    ) {
        AsyncImage(
            model = product.images[1],
            contentDescription = product.name,
            contentScale = ContentScale.Crop
        )
    }
}