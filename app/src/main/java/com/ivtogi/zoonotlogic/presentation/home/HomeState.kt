package com.ivtogi.zoonotlogic.presentation.home

import com.ivtogi.zoonotlogic.domain.model.Product
import com.ivtogi.zoonotlogic.domain.model.User

data class HomeState(
    val userId: String = "",
    val isLoading: Boolean = false,
    val user: User = User(),
    val productList: List<Product> = emptyList(),
)
