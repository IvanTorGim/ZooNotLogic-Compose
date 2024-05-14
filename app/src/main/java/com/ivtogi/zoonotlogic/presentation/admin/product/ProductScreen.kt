package com.ivtogi.zoonotlogic.presentation.admin.product

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddAPhoto
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.ivtogi.zoonotlogic.R
import com.ivtogi.zoonotlogic.domain.model.Size
import com.ivtogi.zoonotlogic.presentation.admin.product.composable.Label
import com.ivtogi.zoonotlogic.presentation.admin.product.composable.NumberField
import com.ivtogi.zoonotlogic.presentation.admin.product.composable.ProductTopBar
import com.ivtogi.zoonotlogic.presentation.admin.product.composable.StockField
import com.ivtogi.zoonotlogic.presentation.admin.product.composable.TextAreaField
import com.ivtogi.zoonotlogic.presentation.admin.product.composable.TextField

@Composable
fun ProductScreen(
    viewModel: ProductViewModel = hiltViewModel(),
    navigateToAdmin: (String) -> Unit
) {
    val state by viewModel.state.collectAsState()

    BackHandler {
        navigateToAdmin(state.userId)
    }

    //TODO: finish screen
    Scaffold(
        topBar = {
            ProductTopBar(
                userId = state.userId,
                label = stringResource(id = R.string.product_label),
                onBackPressed = navigateToAdmin,
                onSavePressed = viewModel::updateProduct,
                onDeletePressed = viewModel::deleteProduct
            )
        }
    ) { paddingValues ->
        if (state.isLoading) {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator()
            }
        } else {
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                Label(text = stringResource(id = R.string.info_label))
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = state.product.name,
                    onValueChange = viewModel::changeName,
                    label = stringResource(id = R.string.name_label)
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextAreaField(
                    value = state.product.description,
                    onValueChange = viewModel::changeDescription,
                    label = stringResource(id = R.string.description_label)
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = state.product.category,
                    onValueChange = viewModel::changeCategory,
                    label = stringResource(id = R.string.category_label)
                )
                Spacer(modifier = Modifier.height(8.dp))
                NumberField(
                    value = String.format("%.2f", state.product.price),
                    onValueChange = viewModel::changePrice,
                    label = stringResource(id = R.string.price_label)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Label(text = stringResource(id = R.string.stock_label))
                Spacer(modifier = Modifier.height(16.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Size.entries.forEach {
                        StockField(
                            size = it.name,
                            value = state.product.stock[it.name].toString(),
                            onValueChange = { size, quantity ->
                                viewModel.changeStock(
                                    size,
                                    quantity
                                )
                            },
                            label = it.name
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Label(text = stringResource(id = R.string.images_label))
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    state.product.images.forEach {
                        AsyncImage(
                            model = it,
                            contentDescription = "image",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(120.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .border(1.dp, Color.Black, RoundedCornerShape(10.dp))
                        )
                    }
                    if (state.product.images.size < 2) {
                        Box(
                            modifier = Modifier
                                .size(120.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .border(1.dp, Color.Black, RoundedCornerShape(10.dp))
                                .clickable { },
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                imageVector = Icons.Outlined.AddAPhoto,
                                contentDescription = stringResource(id = R.string.add_image),
                                modifier = Modifier
                                    .size(60.dp)

                            )
                        }
                    }
                }
            }
        }
    }
}