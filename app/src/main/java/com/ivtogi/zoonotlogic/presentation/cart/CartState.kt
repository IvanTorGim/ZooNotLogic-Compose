package com.ivtogi.zoonotlogic.presentation.cart

import com.ivtogi.zoonotlogic.domain.model.User

data class CartState(
    val userId: String = "",
    val user: User = User(),
    val isLoading: Boolean = false,
)