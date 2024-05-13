package com.ivtogi.zoonotlogic.presentation.home.detail

import com.ivtogi.zoonotlogic.domain.model.Product

data class DetailState(
    val userId: String = "",
    val sizeSelected: String = "",
    val imageSelected: Int = 0,
    val product: Product = Product(),
    val isLoading: Boolean = false,
)