package com.ivtogi.zoonotlogic.data.remote.dto

import com.ivtogi.zoonotlogic.domain.model.Product

data class UserDto(
    val name: String = "",
    val lastName: String = "",
    val email: String = "",
    val phone: String = "",
    val cart: List<Product> = emptyList(),
    val admin: Boolean = false
)