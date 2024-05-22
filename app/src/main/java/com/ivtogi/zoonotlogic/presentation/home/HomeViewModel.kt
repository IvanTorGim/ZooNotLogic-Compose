package com.ivtogi.zoonotlogic.presentation.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ivtogi.zoonotlogic.data.remote.FirestoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val firestoreRepository: FirestoreRepository
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state.asStateFlow()

    init {
        savedStateHandle.get<String>("userId")?.let { id ->
            viewModelScope.launch {
                _state.update { it.copy(isLoading = true) }
                getUser(id)
                getAllProducts()
                _state.update { it.copy(isLoading = false) }
            }
        }
    }

    private suspend fun getAllProducts() {
        _state.update {
            it.copy(
                productList = firestoreRepository.getAllProducts()
            )
        }
    }

    private suspend fun getUser(userId: String) {
        firestoreRepository.getUser(userId)?.let { user ->
            _state.update { it.copy(userId = userId, user = user) }

        }
    }

    fun getTotalCartProducts(): Int =
        _state.value.user.cart.sumOf { cartProduct -> cartProduct.quantity }


    fun getTotalCartProductsAmount(): String {
        val totalPrice: Double =
            _state.value.user.cart.sumOf { cartProduct -> cartProduct.price.toDouble() * cartProduct.quantity }
        return String.format("%.2f", totalPrice)
    }
}