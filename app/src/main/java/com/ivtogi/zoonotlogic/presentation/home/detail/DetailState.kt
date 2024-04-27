package com.ivtogi.zoonotlogic.presentation.home.detail

data class DetailState(
    val size: String = "",
    val imageSelected: Int = 0,
    val images: List<String> = emptyList()
)