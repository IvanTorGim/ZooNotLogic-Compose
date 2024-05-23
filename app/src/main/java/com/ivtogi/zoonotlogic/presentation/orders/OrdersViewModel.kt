package com.ivtogi.zoonotlogic.presentation.orders

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ivtogi.zoonotlogic.data.remote.FirestoreRepository
import com.ivtogi.zoonotlogic.domain.model.Order
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrdersViewModel @Inject constructor(
    saveStateHandle: SavedStateHandle,
    private val firestoreRepository: FirestoreRepository
) : ViewModel() {

    private val _state = MutableStateFlow(OrdersState())
    val state: StateFlow<OrdersState> = _state.asStateFlow()

    init {
        saveStateHandle.get<String>("userId")?.let { userId ->
            viewModelScope.launch {
                _state.update { it.copy(isLoading = true, userId = userId) }
                getAllOrders()
                _state.update { it.copy(isLoading = false) }
            }
        }
    }

    private suspend fun getAllOrders() {
        firestoreRepository.getAllOrders().let { orders ->
            _state.update { it.copy(orders = orders) }
        }
    }

    fun changeSendState(order: Order) {
        val orders = _state.value.orders.toMutableList()
        val index = orders.indexOf(order)
        orders[index] = order.copy(state = "Enviado")
        _state.update { it.copy(orders = orders) }
        firestoreRepository.changeSendState(order.copy(state = "Enviado"))
    }

}