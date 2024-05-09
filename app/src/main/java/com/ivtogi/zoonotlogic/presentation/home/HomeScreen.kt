package com.ivtogi.zoonotlogic.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ivtogi.zoonotlogic.R
import com.ivtogi.zoonotlogic.presentation.home.composable.CartCard
import com.ivtogi.zoonotlogic.presentation.home.composable.NewCollectionProduct
import com.ivtogi.zoonotlogic.presentation.home.composable.ProductCard
import com.ivtogi.zoonotlogic.presentation.home.composable.TopHomeBar

@Composable
fun HomeScreen(
    viewmodel: HomeViewModel = hiltViewModel(),
    navigateToProfile: () -> Unit,
    navigateToCart: (String) -> Unit,
    navigateToAdmin: (String) -> Unit,
    navigateToDetail: (String, String) -> Unit
) {
    val state by viewmodel.state.collectAsState()

    if (state.isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        Scaffold(
            topBar = {
                TopHomeBar(
                    isAdmin = state.user.isAdmin,
                    userId = state.userId,
                    navigateToProfile = navigateToProfile,
                    navigateToAdmin = { navigateToAdmin(it) }
                )
            },
            modifier = Modifier.fillMaxSize()
        ) { paddingValues ->
            Box {
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
                            items(state.productList) { product ->
                                NewCollectionProduct(
                                    product = product,
                                    userId = state.userId,
                                    navigateToDetail = { userId, productId ->
                                        navigateToDetail(
                                            userId,
                                            productId
                                        )
                                    })
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
                    items(state.productList) { product ->
                        ProductCard(
                            product = product,
                            userId = state.userId,
                            navigateToDetail = { userId, productId ->
                                navigateToDetail(
                                    userId,
                                    productId
                                )
                            })
                    }
                }
                if (viewmodel.getTotalCartProducts() > 0) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                    ) {
                        CartCard(
                            userId = state.userId,
                            viewModel = viewmodel,
                            navigateToCart = { navigateToCart(it) }
                        )
                    }
                }
            }
        }
    }

}
