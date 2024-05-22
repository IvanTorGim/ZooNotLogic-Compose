package com.ivtogi.zoonotlogic.domain.model

data class Product(
    val id: String = "",
    val name: String = "",
    val description: String = "",
    val category: String = "",
    val price: String = "",
    val stock: Map<String, Int> = emptyMap(),
    val images: List<String> = emptyList()
)
