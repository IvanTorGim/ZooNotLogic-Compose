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

            savedStateHandle.get<String>("productId")?.let { productId ->
                viewModelScope.launch {
                    _state.update { it.copy(isLoading = true) }
                    val product = firestoreRepository.getProduct(productId)
                    _state.update { it.copy(isLoading = false, product = product) }
                }
            } ?: run {
                _state.update {
                    it.copy(
                        product = Product(
                            name = "",
                            description = "",
                            category = "",
                            price = "0.00",
                            stock = mapOf("XS" to 0, "S" to 0, "M" to 0, "L" to 0, "XL" to 0),
                            images = listOf("", "")
                        )
                    )
                }
            }
        }
    }

    fun showDialog() {
        _state.update { it.copy(showDeleteDialog = true) }
    }

    fun hideDialog() {
        _state.update { it.copy(showDeleteDialog = false) }
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

    fun changePrice(price: String) {
        if (price.matches(Regex("^\\d{0,7}(\\.\\d{0,2})?$")))
            _state.update { it.copy(product = _state.value.product.copy(price = price)) }
    }

    fun changeStock(size: String, quantity: String) {
        val stock = _state.value.product.stock.toMutableMap()
        when {
            quantity.isEmpty() || quantity.toInt() < 0 -> stock[size] = 0
            quantity.toInt() > 100 -> stock[size] = 100
            else -> stock[size] = quantity.toInt()
        }
        _state.update { it.copy(product = _state.value.product.copy(stock = stock)) }
    }

    fun changeImage(image: String, index: Int) {
        val images = _state.value.product.images.toMutableList()
        images[index] = image
        _state.update { it.copy(product = _state.value.product.copy(images = images)) }
    }
}
