package com.ivtogi.zoonotlogic.data.remote.dto

data class CartProductDto(
    val id: String = "",
    val name: String = "",
    val price: Double = 0.0,
    val size: String = "",
    val quantity: Int = 0,
    val image: String = ""
)
