package com.ivtogi.zoonotlogic.presentation.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ivtogi.zoonotlogic.data.mapper.toDomain
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
        val userId = savedStateHandle.get<String>("userId")
        if (!userId.isNullOrBlank()) {
            viewModelScope.launch {
                _state.update { it.copy(isLoading = true) }
                getUser(userId)
                getAllProducts()
                _state.update { it.copy(isLoading = false) }
            }
        }
    }

    private suspend fun getAllProducts() {
        _state.update {
            it.copy(
                productList = firestoreRepository.getAllProducts()
                    .map { product -> product.toDomain() }
            )
        }
    }

    private suspend fun getUser(userId: String) {
        val user = firestoreRepository.getUser(userId)?.toDomain()
        if (user != null) {
            _state.update { it.copy(user = user) }
        }
    }

    fun getCartSize(): Int {
        return _state.value.user.cart.size
    }

    fun getCartTotalAmount(): String {
        val totalPrice: Double = _state.value.user.cart.sumOf { product -> product.price }
        return String.format("%.2f", totalPrice)
    }
}