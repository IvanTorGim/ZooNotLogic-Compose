package com.ivtogi.zoonotlogic.presentation.home.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ivtogi.zoonotlogic.domain.model.Product

@Composable
fun ProductCard(product: Product, userId: String, navigateToDetail: (String, String) -> Unit) {
    Card(
        elevation = CardDefaults.elevatedCardElevation(2.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
            contentColor = Black
        ),
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .clickable { navigateToDetail(userId, product.id) }
    ) {
        Column(Modifier.fillMaxWidth()) {
            AsyncImage(
                model = product.images[0],
                contentDescription = product.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(200.dp)
            )
            Text(
                text = product.name,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(start = 8.dp)
            )
            Text(
                text = "${product.price}€",
                modifier = Modifier.padding(start = 8.dp, bottom = 8.dp)
            )
        }
    }
}