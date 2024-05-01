package com.ivtogi.zoonotlogic.presentation.detail

import com.ivtogi.zoonotlogic.domain.model.Product

data class DetailState(
    val size: String = "",
    val imageSelected: Int = 0,
    val product: Product = Product(),
    val isLoading: Boolean = false
)