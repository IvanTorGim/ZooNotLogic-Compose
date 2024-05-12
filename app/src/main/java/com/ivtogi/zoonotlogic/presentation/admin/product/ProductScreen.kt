package com.ivtogi.zoonotlogic.presentation.admin.product

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ivtogi.zoonotlogic.R
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
    //TODO: finish screen
    Scaffold(
        topBar = {
            ProductTopBar(
                userId = state.userId,
                product = state.product,
                onBackPressed = navigateToAdmin
            )
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            Spacer(modifier = Modifier.height(16.dp))
            Label(text = stringResource(id = R.string.info_label))
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
                value = state.product.price.toString(),
                onValueChange = viewModel::changePrice,
                label = stringResource(id = R.string.price_label)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Label(text = stringResource(id = R.string.stock_label))
            Spacer(modifier = Modifier.height(16.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                state.product.stock.forEach { (size, quantity) ->
                    StockField(
                        size = size,
                        value = quantity.toString(),
                        onValueChange = { s, q -> viewModel.changeStock(s, q) },
                        label = size
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Label(text = stringResource(id = R.string.images_label))
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}