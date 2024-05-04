package com.ivtogi.zoonotlogic.data.remote.dto

import com.ivtogi.zoonotlogic.domain.model.CartProduct

data class UserDto(
    val name: String = "",
    val lastName: String = "",
    val email: String = "",
    val phone: String = "",
    val cart: List<CartProduct> = emptyList(),
    val admin: Boolean = false
)