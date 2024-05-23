package com.ivtogi.zoonotlogic.domain.model

data class Order(
    val orderId: String = "",
    val userId: String = "",
    val cartProducts: List<CartProduct> = emptyList(),
    val state: String = ""
)