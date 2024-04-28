package com.ivtogi.zoonotlogic.data.mapper

import com.ivtogi.zoonotlogic.data.remote.dto.UserDto
import com.ivtogi.zoonotlogic.domain.model.User

fun User.toDto(): UserDto {
    return UserDto(
        name = this.name,
        lastName = this.lastName,
        phone = this.phone,
        email = this.email,
        cart = this.cart,
        admin = this.isAdmin
    )
}

fun UserDto.toDomain(): User {
    return User(
        name = this.name,
        lastName = this.lastName,
        phone = this.phone,
        cart = this.cart,
        email = this.email,
        isAdmin = this.admin
    )
}