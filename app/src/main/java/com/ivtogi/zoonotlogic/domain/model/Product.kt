package com.ivtogi.zoonotlogic.domain.model

data class Product(
    val id: String = "",
    val name: String = "",
    val description: String = "",
    val category: String = "",
    val price: Double = 0.0,
    val stock: Map<Size, Int> = emptyMap(),
    val images: List<String> = emptyList()
)
