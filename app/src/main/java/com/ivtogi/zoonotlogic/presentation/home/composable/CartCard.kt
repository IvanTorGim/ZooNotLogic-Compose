package com.ivtogi.zoonotlogic.presentation.home.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ivtogi.zoonotlogic.R
import com.ivtogi.zoonotlogic.presentation.home.HomeViewModel
import com.ivtogi.zoonotlogic.ui.theme.Primary

@Composable
fun CartCard(
    userId: String,
    viewModel: HomeViewModel,
    navigateToCart: (String) -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Primary),
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .clickable { navigateToCart(userId) }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.ShoppingCart,
                contentDescription = stringResource(id = R.string.see_cart),
            )
            Spacer(modifier = Modifier.width(24.dp))
            Column {
                Text(
                    text = stringResource(
                        id = R.string.product_quantity,
                        viewModel.getTotalCartProducts()
                    ),
                    fontSize = 18.sp
                )
                Text(
                    text = stringResource(
                        id = R.string.total_product_price,
                        viewModel.getTotalCartProductsAmount()
                    ), fontSize = 12.sp
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(text = stringResource(id = R.string.see_cart), fontSize = 18.sp)
        }
    }
}