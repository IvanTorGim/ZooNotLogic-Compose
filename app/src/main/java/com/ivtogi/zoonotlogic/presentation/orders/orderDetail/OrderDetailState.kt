package com.ivtogi.zoonotlogic.presentation.orders.orderDetail

import com.ivtogi.zoonotlogic.domain.model.Order
import com.ivtogi.zoonotlogic.domain.model.User

data class OrderDetailState(
    val user: User = User(),
    val order: Order = Order(),
    val isLoading: Boolean = false,
    val goOrders: Boolean = false
)
