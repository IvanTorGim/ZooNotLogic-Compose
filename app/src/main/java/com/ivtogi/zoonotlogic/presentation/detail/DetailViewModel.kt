package com.ivtogi.zoonotlogic.presentation.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ivtogi.zoonotlogic.data.mapper.toDomain
import com.ivtogi.zoonotlogic.data.remote.FirestoreRepository
import com.ivtogi.zoonotlogic.domain.model.CartProduct
import com.ivtogi.zoonotlogic.domain.model.Size
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val firestoreRepository: FirestoreRepository
) : ViewModel() {
    private val _state = MutableStateFlow(DetailState())
    val state: StateFlow<DetailState> = _state.asStateFlow()

    init {
        val userId = savedStateHandle.get<String>("userId")
        val productId = savedStateHandle.get<String>("productId")
        if (!userId.isNullOrBlank() && !productId.isNullOrBlank()) {
            viewModelScope.launch {
                _state.update { it.copy(isLoading = true, userId = userId) }
                getProduct(productId)
                _state.update { it.copy(isLoading = false) }
            }
        }
    }

    private suspend fun getProduct(productId: String) {
        val product = firestoreRepository.getProduct(productId)?.toDomain()
        if (product != null) {
            _state.update { it.copy(product = product) }
        }
    }

    fun onImageClicked(position: Int) {
        _state.update { it.copy(imageSelected = position) }
    }

    fun onSizeClicked(size: Size) {
        _state.update { it.copy(sizeSelected = size) }
    }

    fun onButtonClicked() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            val user = firestoreRepository.getUser(_state.value.userId)?.toDomain()
            if (user != null) {
                val productInCartList: CartProduct? = user.cart.firstOrNull { p ->
                    p.id == _state.value.product.id && p.size == _state.value.sizeSelected
                }
                val cartProduct = if (productInCartList != null) {
                    val quantity = productInCartList.quantity
                    productInCartList.copy(quantity = quantity + 1)
                } else {
                    CartProduct(
                        id = _state.value.product.id,
                        price = _state.value.product.price,
                        size = _state.value.sizeSelected,
                        quantity = 1,
                        image = _state.value.product.images[0]
                    )
                }
                val cartList = user.cart.toMutableList()
                cartList.add(cartProduct)
                firestoreRepository.insertCart(_state.value.userId, cartList)
            }
            _state.update { it.copy(isLoading = false) }
        }
    }
}