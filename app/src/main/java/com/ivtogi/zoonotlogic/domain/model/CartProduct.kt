package com.ivtogi.zoonotlogic.domain.model

data class CartProduct(
    val id: String = "",
    val price: Double = 0.0,
    val size: Size = Size.NONE,
    val quantity: Int = 0,
    val image: String = ""
)