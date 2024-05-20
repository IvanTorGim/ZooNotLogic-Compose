package com.ivtogi.zoonotlogic.presentation.cart

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
class CartViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val firestoreRepository: FirestoreRepository
) : ViewModel() {

    private val _state = MutableStateFlow(CartState())
    val state: StateFlow<CartState> = _state.asStateFlow()

    init {
        val userId = savedStateHandle.get<String>("userId")
        userId?.let { user ->
            viewModelScope.launch {
                _state.update { it.copy(isLoading = true, userId = user) }
                getUser(user)
                getTotalAmount()
                _state.update { it.copy(isLoading = false) }
            }
        }
    }

    private suspend fun getUser(userId: String) {
        firestoreRepository.getUser(userId)?.let { user ->
            _state.update {
                it.copy(
                    userId = userId,
                    user = user,
                    totalAmount = getTotalAmount().toInt()
                )
            }
        }
    }

    fun removeCartProduct(cartProduct: CartProduct) {
        viewModelScope.launch {
            firestoreRepository.getUser(_state.value.userId)?.let { user ->

                val cartList = user.cart.toMutableList()
                val productInCartList = cartList.find { p ->
                    p.id == cartProduct.id && p.size == cartProduct.size
                }

                productInCartList?.let { cartProduct ->
                    val index = cartList.indexOf(cartProduct)
                    if (cartProduct.quantity > 1) {
                        cartList[index] =
                            cartProduct.copy(quantity = cartProduct.quantity - 1)
                    } else {
                        cartList.removeAt(index)
                    }
                }

                firestoreRepository.updateCart(_state.value.userId, cartList)
                _state.update {
                    it.copy(
                        user = user.copy(cart = cartList),
                        totalAmount = getTotalAmount().toInt()
                    )
                }
            }
        }
    }

    fun addCartProduct(cartProduct: CartProduct) {
        viewModelScope.launch {
            firestoreRepository.getUser(_state.value.userId)?.let { user ->
                val cartList = user.cart.toMutableList()
                val productInCartList = cartList.find { product ->
                    product.id == cartProduct.id && product.size == cartProduct.size
                }

                productInCartList?.let { product ->
                    val index = cartList.indexOf(product)
                    cartList[index] =
                        product.copy(quantity = product.quantity + 1)
                }

                firestoreRepository.updateCart(_state.value.userId, cartList)
                _state.update {
                    it.copy(
                        user = user.copy(cart = cartList),
                        totalAmount = getTotalAmount().toInt()
                    )
                }
            }
        }
    }

    fun getTotalAmount(): Double {
        return _state.value.user.cart.sumOf { it.quantity * it.price }
    }
}