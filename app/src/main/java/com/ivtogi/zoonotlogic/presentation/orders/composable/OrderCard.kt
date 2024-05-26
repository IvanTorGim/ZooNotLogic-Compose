package com.ivtogi.zoonotlogic.presentation.orders.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ivtogi.zoonotlogic.R
import com.ivtogi.zoonotlogic.domain.model.Order
import com.ivtogi.zoonotlogic.presentation.orders.OrdersViewModel

@Composable
fun OrderCard(
    userId: String,
    order: Order,
    viewModel: OrdersViewModel,
    onSendClick: (Order) -> Unit,
    navigateToOrderDetail: (String, String, Boolean) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .clickable {
                navigateToOrderDetail(userId, order.orderId, true)
            }
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            AsyncImage(
                model = order.cartProducts.first().image,
                contentDescription = stringResource(id = R.string.product_image),
                modifier = Modifier.fillMaxHeight()
            )
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(text = "Pedido: ${order.orderId}")
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(
                            modifier = Modifier.fillMaxHeight(),
                            verticalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Text(text = "Fecha: ${viewModel.formatDate(order.date)}")
                            Text(text = "Estado: ${order.state}")
                            Text(text = "Total: ${order.totalPrice}€")
                        }
                        if (order.state == "Pendiente") {
                            Button(
                                onClick = { onSendClick(order) },
                                modifier = Modifier.align(Alignment.CenterVertically)
                            ) {
                                Text(text = "Enviar")
                            }
                        }
                    }
                }
            }
        }
    }
    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .height(2.dp)
    )
}