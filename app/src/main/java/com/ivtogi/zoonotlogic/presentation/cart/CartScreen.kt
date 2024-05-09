package com.ivtogi.zoonotlogic.presentation.cart

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.ivtogi.zoonotlogic.R
import com.ivtogi.zoonotlogic.presentation.cart.composable.CartProductCard
import com.ivtogi.zoonotlogic.presentation.common.DefaultBottomBar
import com.ivtogi.zoonotlogic.presentation.common.DefaultTopBar

@Composable
fun CartScreen(
    viewModel: CartViewModel = hiltViewModel(),
    navigateToHome: (String) -> Unit
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
                    name = stringResource(id = R.string.cart),
                    onBackPressed = { navigateToHome(it) })
            },
            bottomBar = {
                DefaultBottomBar(
                    label = "Realizar pago",
                    onClick = { /* TODO: pago */ }
                )
            },
            modifier = Modifier
                .fillMaxSize()
        ) { paddingValues ->
            LazyColumn(modifier = Modifier.padding(paddingValues)) {
                items(state.user.cart) {
                    CartProductCard(cartProduct = it, viewModel = viewModel)
                }
            }
        }
    }
}