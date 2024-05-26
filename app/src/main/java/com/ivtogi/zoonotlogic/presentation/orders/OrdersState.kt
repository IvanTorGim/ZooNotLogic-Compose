package com.ivtogi.zoonotlogic.presentation.orders

import com.ivtogi.zoonotlogic.domain.model.Order

data class OrdersState(
    val userId: String = "",
    val orders: List<Order> = emptyList(),
    val isLoading: Boolean = false,
)