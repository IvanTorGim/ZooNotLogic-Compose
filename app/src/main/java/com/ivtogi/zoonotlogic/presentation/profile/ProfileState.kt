package com.ivtogi.zoonotlogic.presentation.profile

import com.ivtogi.zoonotlogic.domain.model.Order
import com.ivtogi.zoonotlogic.domain.model.User

data class ProfileState(
    val userId: String = "",
    val user: User = User(),
    val isLoading: Boolean = false,
    val orders: List<Order> = emptyList(),
    val nameError: Boolean = false,
)
