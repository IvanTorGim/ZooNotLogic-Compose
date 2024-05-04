package com.ivtogi.zoonotlogic.presentation.detail

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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ivtogi.zoonotlogic.presentation.detail.composable.Description
import com.ivtogi.zoonotlogic.presentation.detail.composable.DetailBottomBar
import com.ivtogi.zoonotlogic.presentation.detail.composable.DetailTopBar
import com.ivtogi.zoonotlogic.presentation.detail.composable.ImageSelector
import com.ivtogi.zoonotlogic.presentation.detail.composable.ProductImage
import com.ivtogi.zoonotlogic.presentation.detail.composable.ProductPrice
import com.ivtogi.zoonotlogic.presentation.detail.composable.SizeSelector

@Composable
fun DetailScreen(
    viewModel: DetailViewModel = hiltViewModel(),
    onBackPressed: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    if (state.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        Scaffold(
            topBar = { DetailTopBar(name = state.product.name, onBackPressed = onBackPressed) },
            bottomBar = {
                DetailBottomBar(
                    enabled = state.sizeSelected.isNotEmpty(),
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
                    onBackPressed = onBackPressed
                )
                Column(
                    modifier = Modifier.padding(8.dp)
                ) {
                    Spacer(modifier = Modifier.height(8.dp))
                    ImageSelector(
                        images = state.product.images,
                        imageSelected = state.imageSelected,
                        onImageClicked = { viewModel.onImageClicked(it) }
                    )
                    Spacer(modifier = Modifier.height(24.dp))
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