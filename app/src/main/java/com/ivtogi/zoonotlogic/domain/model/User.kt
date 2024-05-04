package com.ivtogi.zoonotlogic.domain.model

data class User(
    val name: String = "",
    val lastName: String = "",
    val phone: String = "",
    val cart: List<CartProduct> = emptyList(),
    val email: String = "",
    val isAdmin: Boolean = false,
    val password: String = ""
)
