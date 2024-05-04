package com.ivtogi.zoonotlogic.data.remote.dto

import com.ivtogi.zoonotlogic.domain.model.Size

data class CartProductDto(
    val id: String = "",
    val size: Size = Size.NONE,
    val quantity: Int = 0,
)
