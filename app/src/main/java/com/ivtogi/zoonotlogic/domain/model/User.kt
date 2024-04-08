package com.ivtogi.zoonotlogic.domain.model

data class User(
    val id: String,
    val name: String,
    val lastName: String,
    val phone: String,
    val cart: List<Product>
)
