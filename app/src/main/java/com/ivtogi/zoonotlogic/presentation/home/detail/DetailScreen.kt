package com.ivtogi.zoonotlogic.presentation.home.detail

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
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
import com.ivtogi.zoonotlogic.presentation.composable.DefaultBottomBar
import com.ivtogi.zoonotlogic.presentation.composable.DefaultTopBar
import com.ivtogi.zoonotlogic.presentation.home.detail.composable.Description
import com.ivtogi.zoonotlogic.presentation.home.detail.composable.ProductImage
import com.ivtogi.zoonotlogic.presentation.home.detail.composable.ProductPrice
import com.ivtogi.zoonotlogic.presentation.home.detail.composable.SizeSelector

@Composable
fun DetailScreen(
    viewModel: DetailViewModel = hiltViewModel(),
    navigateToHome: (String) -> Unit
) {
    val state by viewModel.state.collectAsState()
    // TODO: Añadir snackbar para avisar que el maximo de productos que se pueden agregar son 3
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
                    name = state.product.name,
                    onBackPressed = { navigateToHome(it) })
            },
            bottomBar = {
                DefaultBottomBar(
                    label = stringResource(id = R.string.add_cart),
                    enabled = state.sizeSelected.isNotBlank(),
                    onClick = { viewModel.onButtonClicked() }
                )
            },
            modifier = Modifier
                .fillMaxSize()
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .verticalScroll(rememberScrollState())
            ) {
                ProductImage(
                    image = state.product.images[state.imageSelected],
                    viewModel = viewModel
                )
                Column(
                    modifier = Modifier.padding(horizontal = 8.dp)
                ) {
                    Spacer(modifier = Modifier.height(16.dp))
                    ProductPrice(price = String.format("%.2f€", state.product.price))
                    Spacer(modifier = Modifier.height(16.dp))
                    Divider(modifier = Modifier.fillMaxWidth())
                    Spacer(modifier = Modifier.height(16.dp))
                    SizeSelector(
                        sizeSelected = state.sizeSelected,
                        onSizeClicked = { viewModel.onSizeClicked(it) }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Divider(modifier = Modifier.fillMaxSize())
                    Spacer(modifier = Modifier.height(16.dp))
                    Description(description = state.product.description)
                }
            }
        }
    }
}