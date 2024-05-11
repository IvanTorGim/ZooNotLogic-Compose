package com.ivtogi.zoonotlogic.presentation.cart.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.ivtogi.zoonotlogic.domain.model.CartProduct
import com.ivtogi.zoonotlogic.presentation.cart.CartViewModel

@Composable
fun CartProductCard(
    cartProduct: CartProduct,
    viewModel: CartViewModel
) {
    Card(
        elevation = CardDefaults.elevatedCardElevation(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
            contentColor = Black
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(vertical = 8.dp, horizontal = 16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            AsyncImage(model = cartProduct.image, contentDescription = cartProduct.name)
            Spacer(modifier = Modifier.padding(8.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxSize()
            ) {
                Column {
                    Text(
                        text = cartProduct.name,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(text = cartProduct.size.name)
                    Text(
                        text = String.format("%.2f€", cartProduct.price * cartProduct.quantity)
                    )
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(
                        onClick = { viewModel.removeCartProduct(cartProduct) }
                    ) {
                        Icon(
                            imageVector = if (cartProduct.quantity == 1) Icons.Filled.Delete else Icons.Filled.Remove,
                            contentDescription = "Remove"
                        )
                    }
                    Spacer(modifier = Modifier.padding(2.dp))
                    Text(text = cartProduct.quantity.toString())
                    Spacer(modifier = Modifier.padding(2.dp))
                    IconButton(
                        onClick = { viewModel.addCartProduct(cartProduct) },
                        enabled = cartProduct.quantity < 3
                    ) {
                        Icon(imageVector = Icons.Filled.Add, contentDescription = "Add")
                    }
                }
            }
        }
    }
}