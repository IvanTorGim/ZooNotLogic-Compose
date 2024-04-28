package com.ivtogi.zoonotlogic.presentation.detail

data class DetailState(
    val size: String = "",
    val imageSelected: Int = 0,
    val images: List<String> = emptyList()
)