package com.ivtogi.zoonotlogic.data.mapper

import com.ivtogi.zoonotlogic.data.remote.dto.CartProductDto
import com.ivtogi.zoonotlogic.domain.model.CartProduct

fun CartProductDto.toDomain(): CartProduct {
    return CartProduct(
        id = this.id,
        size = this.size,
        quantity = this.quantity
    )
}

fun CartProduct.toDto(): CartProductDto {
    return CartProductDto(
        id = this.id,
        size = this.size,
        quantity = this.quantity
    )
}