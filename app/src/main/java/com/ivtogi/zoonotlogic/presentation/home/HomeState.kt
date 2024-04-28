package com.ivtogi.zoonotlogic.presentation.home

import com.ivtogi.zoonotlogic.domain.model.Product

data class HomeState(
    val userId: String = "",
    val isLoading: Boolean = false,
    val isAdmin: Boolean = false,
    val productList: List<Product> = emptyList()
)
