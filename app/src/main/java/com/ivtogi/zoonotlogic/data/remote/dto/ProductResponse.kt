package com.ivtogi.zoonotlogic.data.remote.dto

data class ProductResponse(
    val name: String = "",
    val description: String = "",
    val category: String = "",
    val price: String = "",
    val images: List<String> = emptyList()
)
