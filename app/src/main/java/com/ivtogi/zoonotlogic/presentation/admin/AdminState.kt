package com.ivtogi.zoonotlogic.presentation.admin

import com.ivtogi.zoonotlogic.domain.model.Product

data class AdminState(
    val productList: List<Product> = emptyList(),
    val isLoading: Boolean = false,
    val userId: String = ""
)
