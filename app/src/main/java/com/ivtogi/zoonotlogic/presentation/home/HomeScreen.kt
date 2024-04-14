package com.ivtogi.zoonotlogic.presentation.home

import android.view.Window
import android.view.WindowManager
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.ivtogi.zoonotlogic.R
import com.ivtogi.zoonotlogic.domain.model.Product
import com.ivtogi.zoonotlogic.presentation.home.composable.NewCollectionProduct
import com.ivtogi.zoonotlogic.presentation.home.composable.ProductCard

@Composable
fun HomeScreen(viewmodel: HomeViewModel = hiltViewModel()) {
    val state by viewmodel.state.collectAsState()

    LazyVerticalGrid(
        columns = GridCells.Adaptive(180.dp),
        modifier = Modifier
            .fillMaxSize()
    ) {
        item(span = { GridItemSpan(maxLineSpan) }) {
            Text(
                text = stringResource(id = R.string.new_collection_products),
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(8.dp)
            )
        }

        item(span = { GridItemSpan(maxLineSpan) }) {
            LazyRow(modifier = Modifier.height(200.dp)) {
                items(state.productList) {
                    NewCollectionProduct(product = it)
                }
            }
        }

        item(span = { GridItemSpan(maxLineSpan) }) {
            Text(
                text = stringResource(id = R.string.all_products),
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(8.dp)
            )
        }
        items(state.productList) {
            ProductCard(product = it)
        }
    }
}
