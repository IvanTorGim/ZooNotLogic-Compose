package com.ivtogi.zoonotlogic.domain.model

data class CartProduct(
    val id: String = "",
    val name: String = "",
    val price: String = "",
    val size: String = "",
    val quantity: Int = 0,
    val image: String = ""
)
