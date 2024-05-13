package com.ivtogi.zoonotlogic.presentation.admin.product

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ivtogi.zoonotlogic.data.remote.FirestoreRepository
import com.ivtogi.zoonotlogic.domain.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val firestoreRepository: FirestoreRepository
) : ViewModel() {

    private val _state = MutableStateFlow(ProductState())
    val state = _state.asStateFlow()

    init {
        savedStateHandle.get<String>("userId")?.let { userId ->
            _state.update { it.copy(userId = userId) }
        }
        savedStateHandle.get<String>("productId")?.let { productId ->
            viewModelScope.launch {
                _state.update { it.copy(isLoading = true) }
                getProduct(productId)
                _state.update { it.copy(isLoading = false) }
            }
        } ?: run {
            _state.update {
                it.copy(
                    product = Product(
                        name = "",
                        description = "",
                        category = "",
                        price = 0.0,
                        stock = mapOf("XS" to 0, "S" to 0, "M" to 0, "L" to 0, "XL" to 0)
                    )
                )
            }
        }
    }

    private suspend fun getProduct(productId: String) {
        firestoreRepository.getProduct(productId)?.let { product ->
            Log.i("ivan", product.toString())
            _state.update { it.copy(product = product) }
        }
    }

    fun changeName(name: String) {
        _state.update { it.copy(product = _state.value.product.copy(name = name)) }
    }

    fun changeDescription(description: String) {
        _state.update { it.copy(product = _state.value.product.copy(description = description)) }
    }

    fun changeCategory(category: String) {
        _state.update { it.copy(product = _state.value.product.copy(category = category)) }
    }

    fun changePrice(price: String) {
        _state.update { it.copy(product = _state.value.product.copy(price = price.toDouble())) }
    }

    fun changeStock(size: String, quantity: String) {
        val number = if (quantity.isBlank()) 0 else quantity.toInt()
        val stock = _state.value.product.stock.toMutableMap()
        if (number < 0) {
            stock[size] = 0
            _state.update { it.copy(product = _state.value.product.copy(stock = stock)) }
        } else if (number > 100) {
            stock[size] = 100
            _state.update { it.copy(product = _state.value.product.copy(stock = stock)) }
        } else {
            stock[size] = number
            _state.update { it.copy(product = _state.value.product.copy(stock = stock)) }
        }
    }
}