package com.ivtogi.zoonotlogic.presentation.home.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
        val userId = savedStateHandle.get<String>("userId").orEmpty()
        val productId = savedStateHandle.get<String>("productId").orEmpty()
        if (userId.isNotEmpty() && productId.isNotEmpty()) {
            viewModelScope.launch {
                _state.update { it.copy(isLoading = true, userId = userId) }
                getProduct(productId)
                _state.update { it.copy(isLoading = false) }
            }
        }
    }

    private suspend fun getProduct(productId: String) {
        firestoreRepository.getProduct(productId)?.let { product ->
            _state.update { it.copy(product = product) }
        }
    }

    fun nextImage() {
        val imagesSize = _state.value.product.images.size
        val imageSelected = if (_state.value.imageSelected >= imagesSize - 1) 0
        else _state.value.imageSelected + 1
        _state.update { it.copy(imageSelected = imageSelected) }
    }

    fun previousImage() {
        val imagesSize = _state.value.product.images.size
        val imageSelected = if (_state.value.imageSelected <= 0) imagesSize - 1
        else _state.value.imageSelected - 1
        _state.update { it.copy(imageSelected = imageSelected) }
    }

    fun onSizeClicked(size: Size) {
        _state.update { it.copy(sizeSelected = size) }
    }

    fun onButtonClicked() {
        viewModelScope.launch {
            firestoreRepository.getUser(_state.value.userId)?.let { user ->

                val cartList = user.cart.toMutableList()
                val productInCartList = cartList.find { cartProduct ->
                    cartProduct.id == state.value.product.id && cartProduct.size == _state.value.sizeSelected
                }

                productInCartList?.let { cartProduct ->
                    if (cartProduct.quantity < 3) {
                        val index = cartList.indexOf(cartProduct)
                        cartList[index] =
                            cartProduct.copy(quantity = cartProduct.quantity + 1)
                    }
                } ?: run {
                    val cartProduct = CartProduct(
                        id = _state.value.product.id,
                        name = _state.value.product.name,
                        price = _state.value.product.price,
                        size = _state.value.sizeSelected,
                        quantity = 1,
                        image = _state.value.product.images[0]
                    )
                    cartList.add(cartProduct)
                }

                firestoreRepository.updateCart(_state.value.userId, cartList)
            }
        }
    }
}