package com.ivtogi.zoonotlogic.presentation.admin.product

import com.ivtogi.zoonotlogic.domain.model.Product

data class ProductState(
    val userId: String = "",
    val product: Product = Product(),
    val isLoading: Boolean = false,
    val showDeleteDialog: Boolean = false
)
