package com.ivtogi.zoonotlogic.presentation.home.detail

import androidx.compose.material3.SnackbarHostState
import com.ivtogi.zoonotlogic.domain.model.Product
import com.ivtogi.zoonotlogic.domain.model.User

data class DetailState(
    val userId: String = "",
    val user: User = User(),
    val sizeSelected: String = "",
    val imageSelected: Int = 0,
    val product: Product = Product(),
    val isLoading: Boolean = false,
    val message: String = "",
    val snackbarHostState: SnackbarHostState = SnackbarHostState()
)