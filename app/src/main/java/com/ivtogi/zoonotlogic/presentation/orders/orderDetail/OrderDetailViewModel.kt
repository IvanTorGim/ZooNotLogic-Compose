package com.ivtogi.zoonotlogic.presentation.orders.orderDetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ivtogi.zoonotlogic.data.remote.FirestoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class OrderDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val firestoreRepository: FirestoreRepository
) : ViewModel() {

    private val _state = MutableStateFlow(OrderDetailState())
    val state: StateFlow<OrderDetailState> = _state

    init {
        savedStateHandle.get<String>("userId")?.let { userId ->
            savedStateHandle.get<Boolean>("goOrders")?.let { goOrders ->
                savedStateHandle.get<String>("orderId")?.let { orderId ->
                    viewModelScope.launch {
                        _state.update { it.copy(isLoading = true) }
                        val user = firestoreRepository.getUser(userId)
                        val order = firestoreRepository.getOrder(orderId)
                        _state.update {
                            it.copy(
                                user = user,
                                order = order,
                                goOrders = goOrders,
                                isLoading = false
                            )
                        }
                    }
                }
            }
        }
    }

    fun formatDate(date: Long): String {
        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return sdf.format(Date(date))
    }
}

