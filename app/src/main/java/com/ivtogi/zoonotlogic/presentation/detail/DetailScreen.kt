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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.ivtogi.zoonotlogic.R
import com.ivtogi.zoonotlogic.presentation.common.DefaultButton
import com.ivtogi.zoonotlogic.presentation.detail.composable.ImageSelector
import com.ivtogi.zoonotlogic.presentation.detail.composable.ProductNameAndPrice
import com.ivtogi.zoonotlogic.presentation.detail.composable.SizeSelector

@Composable
fun DetailScreen(
    viewModel: DetailViewModel = hiltViewModel(),
    onBackPressed: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            Box(modifier = Modifier.fillMaxWidth()) {
                AsyncImage(
                    model = state.images[state.imageSelected],
                    contentDescription = "",
                    placeholder = painterResource(id = R.drawable.butterfly),
                    modifier = Modifier.fillMaxWidth()
                )
                IconButton(onClick = { onBackPressed() }) {
                    Icon(
                        imageVector = Icons.Outlined.ArrowBack,
                        contentDescription = stringResource(id = R.string.arrow_back)
                    )
                }
            }
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                Spacer(modifier = Modifier.height(8.dp))
                ImageSelector(
                    images = state.images,
                    imageSelected = state.imageSelected,
                    onImageClicked = { viewModel.onImageClicked(it) }
                )
                Spacer(modifier = Modifier.height(24.dp))
                ProductNameAndPrice(name = "Nombre del producto", price = "30.00€")
                Spacer(modifier = Modifier.height(16.dp))
                Divider(modifier = Modifier.fillMaxWidth())
                Spacer(modifier = Modifier.height(16.dp))
                SizeSelector { /*TODO: hacer que cambie el color al seleccionar*/ }

            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .align(Alignment.BottomCenter)
        ) {
            DefaultButton(
                label = stringResource(id = R.string.add_cart),
                loading = false,
                onClick = {/*TODO: Añadir al carro*/ }
            )
        }
    }
}