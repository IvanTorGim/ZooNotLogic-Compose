package com.ivtogi.zoonotlogic.data.remote.dto

import com.ivtogi.zoonotlogic.domain.model.Size

data class CartProductDto(
    val id: String = "",
    val name: String = "",
    val price: Double = 0.0,
    val size: Size = Size.NONE,
    val quantity: Int = 0,
    val image: String = ""
)
