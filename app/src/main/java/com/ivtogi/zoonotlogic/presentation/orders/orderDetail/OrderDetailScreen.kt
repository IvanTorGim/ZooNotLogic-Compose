package com.ivtogi.zoonotlogic.presentation.orders.orderDetail

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.ivtogi.zoonotlogic.R
import com.ivtogi.zoonotlogic.presentation.composable.DefaultTopBar

@Composable
fun OrderDetailScreen(
    viewModel: OrderDetailViewModel = hiltViewModel(),
    navigateToProfile: (String) -> Unit,
    navigateToOrders: (String) -> Unit,
) {
    val state by viewModel.state.collectAsState()

    BackHandler {
        if (state.goOrders) {
            navigateToOrders(state.user.id)
        } else {
            navigateToProfile(state.user.id)
        }
    }

    if (state.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        Scaffold(
            topBar = {
                DefaultTopBar(
                    userId = state.user.id,
                    name = stringResource(id = R.string.order_detail),
                    onBackPressed = {
                        if (state.goOrders) {
                            navigateToOrders(state.user.id)
                        } else {
                            navigateToProfile(state.user.id)
                        }
                    }
                )
            }
        ) { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(8.dp)
            ) {
                item {
                    Text(
                        text = stringResource(
                            id = R.string.order_detail_order_id, state.order.orderId
                        )
                    )
                    Text(
                        text = stringResource(
                            id = R.string.order_detail_date, viewModel.formatDate(state.order.date)
                        )
                    )
                    Text(
                        text = stringResource(
                            id = R.string.order_detail_user_name, state.order.userName
                        )
                    )
                    Text(
                        text = stringResource(
                            id = R.string.order_detail_user_phone,
                            state.order.userPhone
                        )
                    )
                    Text(
                        text = stringResource(
                            id = R.string.order_detail_address,
                            state.order.address
                        )
                    )
                    Text(text = stringResource(id = R.string.order_detail_city, state.order.city))
                    Text(
                        text = stringResource(
                            id = R.string.order_detail_postal_code,
                            state.order.postalCode
                        )
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Divider(modifier = Modifier.fillMaxWidth())
                    Spacer(modifier = Modifier.height(8.dp))
                }
                items(state.order.cartProducts) { product ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        AsyncImage(
                            model = product.image,
                            contentDescription = stringResource(id = R.string.product_image),
                            modifier = Modifier
                                .height(80.dp)
                                .padding(end = 16.dp)
                        )
                        Column(
                            modifier = Modifier
                                .padding(end = 8.dp),
                        ) {
                            Text(
                                text = stringResource(
                                    id = R.string.order_detail_quantity,
                                    product.quantity.toString()
                                )
                            )
                            Text(
                                text = stringResource(
                                    id = R.string.order_detail_size,
                                    product.size
                                )
                            )
                            Text(
                                text = stringResource(
                                    id = R.string.order_detail_price,
                                    product.price
                                )
                            )
                        }
                    }
                }
                item {
                    Spacer(modifier = Modifier.height(8.dp))
                    Divider(modifier = Modifier.fillMaxWidth())
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = stringResource(
                            id = R.string.order_detail_total_price,
                            state.order.totalPrice
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 8.dp),
                        textAlign = TextAlign.End
                    )
                }
            }
        }
    }
}