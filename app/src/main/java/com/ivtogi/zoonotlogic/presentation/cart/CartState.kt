package com.ivtogi.zoonotlogic.presentation.cart

import com.ivtogi.zoonotlogic.domain.model.User

data class CartState(
    val user: User = User(),
    val address: String = "",
    val city: String = "",
    val state: String = "",
    val isLoading: Boolean = false,
    val validAddress: Boolean = false,
    val showBottomSheet: Boolean = false
)