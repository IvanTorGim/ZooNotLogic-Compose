package com.ivtogi.zoonotlogic.presentation.home.detail

import com.ivtogi.zoonotlogic.domain.model.Product
import com.ivtogi.zoonotlogic.domain.model.Size
import com.ivtogi.zoonotlogic.domain.model.Size.NONE

data class DetailState(
    val userId: String = "",
    val sizeSelected: Size = NONE,
    val imageSelected: Int = 0,
    val product: Product = Product(),
    val isLoading: Boolean = false,
)