package com.ivtogi.zoonotlogic.presentation.admin.product

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
                        stock = mapOf("XS" to 0, "S" to 0, "M" to 0, "L" to 0, "XL" to 0),
                        images = listOf("https://firebasestorage.googleapis.com/v0/b/zoo-not-logic.appspot.com/o/products%2Fbutterfly.png?alt=media&token=a1c4fd6b-e794-44b2-b7c6-e7ee72b12034")
                    )
                )
            }
        }
    }

    fun showDialog() {
        _state.update { it.copy(showDeleteDialog = true) }
    }

    fun hideDialog() {
        _state.update { it.copy(showDeleteDialog = false) }
    }

    private suspend fun getProduct(productId: String) {
        firestoreRepository.getProduct(productId)?.let { product ->
            _state.update { it.copy(product = product) }
        }
    }

    fun updateProduct() {
        firestoreRepository.insertProduct(_state.value.product)
    }

    fun deleteProduct() {
        firestoreRepository.deleteProduct(_state.value.product.id)
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

    //TODO: fix decimals
    fun changePrice(price: String) {
        if (price.matches(Regex("^\\d{0,7}(\\.\\d{0,2})?$"))) {
            _state.update { it.copy(product = _state.value.product.copy(price = price.toDouble())) }
        }
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