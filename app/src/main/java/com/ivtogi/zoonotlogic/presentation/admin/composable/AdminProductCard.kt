package com.ivtogi.zoonotlogic.presentation.admin.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ivtogi.zoonotlogic.R
import com.ivtogi.zoonotlogic.domain.model.Product

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminProductCard(
    product: Product
) {
    Card(
        onClick = { /*TODO*/ },
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
            .padding(horizontal = 8.dp, vertical = 4.dp),
        elevation = CardDefaults.elevatedCardElevation(),
    ) {
        AsyncImage(
            model = product.images[0],
            contentScale = ContentScale.Crop,
            contentDescription = stringResource(id = R.string.product_image),
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}