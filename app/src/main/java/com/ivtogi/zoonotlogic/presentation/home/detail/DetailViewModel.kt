package com.ivtogi.zoonotlogic.presentation.home.detail

import androidx.compose.material3.SnackbarDuration
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ivtogi.zoonotlogic.data.remote.FirestoreRepository
import com.ivtogi.zoonotlogic.domain.model.CartProduct
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
        savedStateHandle.get<String>("userId")?.let { userId ->
            savedStateHandle.get<String>("productId")?.let { productId ->
                viewModelScope.launch {
                    _state.update { it.copy(isLoading = true, userId = userId) }
                    val user = firestoreRepository.getUser(userId)
                    val product = firestoreRepository.getProduct(productId)
                    _state.update { it.copy(isLoading = false, user = user, product = product) }
                }
            }
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

    fun onSizeClicked(size: String) {
        _state.update { it.copy(sizeSelected = size) }
    }

    fun onButtonClicked() {
        viewModelScope.launch {
            firestoreRepository.getUser(_state.value.userId).let { user ->
                var message = "Producto añadido al carrito"
                val cartList = user.cart.toMutableList()
                val productInCartList = cartList.find { cartProduct ->
                    cartProduct.id == state.value.product.id && cartProduct.size == _state.value.sizeSelected
                }

                productInCartList?.let { cartProduct ->
                    if (cartProduct.quantity < 3) {
                        val index = cartList.indexOf(cartProduct)
                        cartList[index] =
                            cartProduct.copy(quantity = cartProduct.quantity + 1)
                    } else {
                        message = "El máximo de productos de la misma talla son 3"
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
                _state.value.snackbarHostState.showSnackbar(
                    message = message,
                    duration = SnackbarDuration.Short
                )
            }
        }
    }
}