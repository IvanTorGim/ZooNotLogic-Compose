package com.ivtogi.zoonotlogic.presentation.admin.product

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ivtogi.zoonotlogic.data.remote.FirestoreRepository
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
        }
    }

    private suspend fun getProduct(productId: String) {
        firestoreRepository.getProduct(productId)?.let { product ->
            _state.update { it.copy(product = product) }
        }
    }

}