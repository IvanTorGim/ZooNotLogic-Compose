package com.ivtogi.zoonotlogic.presentation.admin.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ivtogi.zoonotlogic.R
import com.ivtogi.zoonotlogic.domain.model.Product

@Composable
fun AdminProductCard(
    product: Product
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(16.dp),
        elevation = CardDefaults.elevatedCardElevation()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            AsyncImage(
                model = product.images[0],
                contentDescription = stringResource(id = R.string.product_image)
            )
            Column(
                modifier = Modifier
                    .padding(start = 16.dp)
            ) {
                Text(text = product.name)
                Spacer(modifier = Modifier.height(8.dp))
                product.stock.forEach { (size, quantity) ->
                    Text(text = "${size.name}: $quantity")
                }
            }
        }
    }
}