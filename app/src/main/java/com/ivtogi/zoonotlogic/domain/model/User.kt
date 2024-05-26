package com.ivtogi.zoonotlogic.domain.model

data class User(
    val id: String = "",
    val name: String = "",
    val lastName: String = "",
    val phone: String = "",
    val cart: List<CartProduct> = emptyList(),
    val email: String = "",
    val isAdmin: Boolean = false,
    val address: String = "",
    val city: String = "",
    val postalCode: String = "",
    val password: String = ""
)
