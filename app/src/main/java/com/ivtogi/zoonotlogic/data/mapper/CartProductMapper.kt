package com.ivtogi.zoonotlogic.data.mapper

import com.ivtogi.zoonotlogic.data.remote.dto.CartProductDto
import com.ivtogi.zoonotlogic.domain.model.CartProduct

fun CartProductDto.toDomain(): CartProduct {
    return CartProduct(
        id = this.id,
        name = this.name,
        price = this.price,
        size = this.size,
        quantity = this.quantity,
        image = this.image
    )
}

fun CartProduct.toDto(): CartProductDto {
    return CartProductDto(
        id = this.id,
        name = this.name,
        price = this.price,
        size = this.size,
        quantity = this.quantity,
        image = this.image
    )
}