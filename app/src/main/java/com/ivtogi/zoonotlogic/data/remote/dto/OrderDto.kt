package com.ivtogi.zoonotlogic.data.remote.dto

import com.ivtogi.zoonotlogic.domain.model.CartProduct

data class OrderDto(
    val userId: String = "",
    val cartProducts: List<CartProduct> = emptyList(),
    val state: String = ""
)
