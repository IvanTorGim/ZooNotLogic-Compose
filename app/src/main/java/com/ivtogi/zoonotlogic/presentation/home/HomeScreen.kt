package com.ivtogi.zoonotlogic.presentation.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ivtogi.zoonotlogic.R
import com.ivtogi.zoonotlogic.presentation.home.composable.NewCollectionProduct
import com.ivtogi.zoonotlogic.presentation.home.composable.ProductCard
import com.ivtogi.zoonotlogic.presentation.home.composable.TopHomeBar

@Composable
fun HomeScreen(
    viewmodel: HomeViewModel = hiltViewModel(),
    navigateToProfile: () -> Unit,
    navigateToAdmin: () -> Unit,
    navigateToDetail: () -> Unit
) {
    val state by viewmodel.state.collectAsState()

    Scaffold(
        topBar = {
            TopHomeBar(
                navigateToProfile = navigateToProfile,
                navigateToAdmin = navigateToAdmin
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        LazyVerticalGrid(
            columns = GridCells.Adaptive(180.dp),
            modifier = Modifier
                .padding(paddingValues)
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
                        NewCollectionProduct(
                            product = it,
                            onClick = navigateToDetail
                        )
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
                ProductCard(product = it, navigateToDetail = navigateToDetail)
            }
        }
    }


}
