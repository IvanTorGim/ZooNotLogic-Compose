package com.ivtogi.zoonotlogic.presentation.orders

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.ivtogi.zoonotlogic.R
import com.ivtogi.zoonotlogic.presentation.composable.DefaultTopBar
import com.ivtogi.zoonotlogic.presentation.orders.composable.OrderCard

@Composable
fun OrdersScreen(
    viewModel: OrdersViewModel = hiltViewModel(),
    navigateToHome: (String) -> Unit,
    navigateToOrderDetail: (String, String, Boolean) -> Unit
) {
    val state by viewModel.state.collectAsState()

    BackHandler {
        navigateToHome(state.userId)
    }

    if (state.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        Scaffold(
            topBar = {
                DefaultTopBar(
                    userId = state.userId,
                    name = stringResource(id = R.string.orders),
                    onBackPressed = { navigateToHome(it) })
            },
            modifier = Modifier
                .fillMaxSize()
        ) { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                LazyColumn {
                    item { Divider(modifier = Modifier.fillMaxWidth()) }
                    items(state.orders) { order ->
                        OrderCard(
                            userId = state.userId,
                            order = order,
                            viewModel = viewModel,
                            onSendClick = { viewModel.changeSendState(it) },
                            navigateToOrderDetail = { userId, orderId, goOrders ->
                                navigateToOrderDetail(userId, orderId, goOrders)
                            }
                        )
                    }
                }
            }
        }
    }
}