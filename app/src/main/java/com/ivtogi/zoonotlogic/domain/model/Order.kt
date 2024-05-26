package com.ivtogi.zoonotlogic.domain.model

data class Order(
    val orderId: String = "",
    val userId: String = "",
    val cartProducts: List<CartProduct> = emptyList(),
    val state: String = "",
    val totalPrice: String = "",
    val address: String = "",
    val city: String = "",
    val postalCode: String = "",
    val date: Long = 0
)