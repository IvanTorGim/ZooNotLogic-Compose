package com.ivtogi.zoonotlogic.ui.home

import com.ivtogi.zoonotlogic.domain.model.Product

data class HomeState(
    val productList: List<Product> = emptyList()
)
