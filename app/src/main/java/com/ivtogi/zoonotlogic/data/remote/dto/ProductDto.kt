package com.ivtogi.zoonotlogic.data.remote.dto

data class ProductDto(
    val id: String = "",
    val name: String = "",
    val description: String = "",
    val category: String = "",
    val price: Double = 0.0,
    val images: List<String> = emptyList()
)
