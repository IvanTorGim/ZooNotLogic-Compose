package com.ivtogi.zoonotlogic.data.mapper

import com.ivtogi.zoonotlogic.data.remote.dto.OrderDto
import com.ivtogi.zoonotlogic.domain.model.Order

fun Order.toDto(): OrderDto {
    return OrderDto(
        userId = this.userId,
        cartProducts = this.cartProducts,
        state = this.state,
        totalPrice = this.totalPrice,
        address = this.address,
        city = this.city,
        postalCode = this.postalCode,
        date = this.date
    )
}

fun OrderDto.toDomain(): Order {
    return Order(
        userId = this.userId,
        cartProducts = this.cartProducts,
        state = this.state,
        totalPrice = this.totalPrice,
        address = this.address,
        city = this.city,
        postalCode = this.postalCode,
        date = this.date
    )
}