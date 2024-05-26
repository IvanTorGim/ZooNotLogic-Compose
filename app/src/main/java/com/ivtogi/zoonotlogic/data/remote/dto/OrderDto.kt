package com.ivtogi.zoonotlogic.data.remote.dto

import com.ivtogi.zoonotlogic.domain.model.CartProduct

data class OrderDto(
    val userId: String = "",
    val userName: String = "",
    val userPhone: String = "",
    val cartProducts: List<CartProduct> = emptyList(),
    val state: String = "",
    val totalPrice: String = "",
    val address: String = "",
    val city: String = "",
    val postalCode: String = "",
    val date: Long = 0
)
