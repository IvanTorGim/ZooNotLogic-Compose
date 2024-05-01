package com.ivtogi.zoonotlogic.domain.model

data class User(
    val name: String = "",
    val lastName: String = "",
    val phone: String = "",
    val cart: List<Product> = emptyList(),
    val email: String = "",
    val isAdmin: Boolean = false,
    val password: String = ""
)
