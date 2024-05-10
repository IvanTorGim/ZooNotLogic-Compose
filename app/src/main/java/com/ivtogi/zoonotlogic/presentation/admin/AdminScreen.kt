package com.ivtogi.zoonotlogic.presentation.admin

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ivtogi.zoonotlogic.R
import com.ivtogi.zoonotlogic.presentation.admin.composable.AdminProductCard
import com.ivtogi.zoonotlogic.presentation.common.DefaultTopBar
import com.ivtogi.zoonotlogic.ui.theme.Primary

@Composable
fun AdminScreen(
    viewModel: AdminViewModel = hiltViewModel(),
    navigateToHome: (String) -> Unit,
    navigateToProduct: (String, String) -> Unit
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
                    name = stringResource(id = R.string.admin),
                    onBackPressed = { navigateToHome(it) })
            },
            modifier = Modifier
                .fillMaxSize()
        ) { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                LazyColumn(Modifier.fillMaxSize()) {
                    val productsSorted =
                        state.productList.sortedBy { product -> product.stock.minOf { stock -> stock.value } }
                    items(productsSorted) { product ->
                        AdminProductCard(
                            userId = state.userId,
                            product = product,
                            navigateToProduct = { userId, productId ->
                                navigateToProduct(
                                    userId,
                                    productId
                                )
                            })
                    }
                }
                FloatingActionButton(
                    onClick = { /*TODO*/ },
                    containerColor = Primary,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(20.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = stringResource(id = R.string.add_product)
                    )
                }
            }
        }
    }
}