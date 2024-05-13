package com.ivtogi.zoonotlogic.presentation.admin.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ivtogi.zoonotlogic.R
import com.ivtogi.zoonotlogic.domain.model.Product
import com.ivtogi.zoonotlogic.ui.theme.StockEmpty
import com.ivtogi.zoonotlogic.ui.theme.StockFull
import com.ivtogi.zoonotlogic.ui.theme.StockWarning

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminProductCard(
    userId: String,
    product: Product,
    navigateToProduct: (String, String) -> Unit
) {
    Card(
        onClick = { navigateToProduct(userId, product.id) },
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
            .padding(horizontal = 8.dp, vertical = 4.dp),
        elevation = CardDefaults.elevatedCardElevation(2.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            AsyncImage(
                model = product.images[0],
                contentScale = ContentScale.Crop,
                contentDescription = stringResource(id = R.string.product_image),
                modifier = Modifier
                    .fillMaxWidth()
            )
            Icon(
                imageVector = Icons.Filled.Circle,
                contentDescription = "Stock Status",
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(end = 8.dp, top = 4.dp),
                tint = if (product.stock.any { stock -> stock.value < 5 }) {
                    StockEmpty
                } else if (product.stock.any { stock -> stock.value < 10 }) {
                    StockWarning
                } else {
                    StockFull
                }
            )
        }
    }
}