package com.ivtogi.zoonotlogic.presentation.cart

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
class CartViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val firestoreRepository: FirestoreRepository
) : ViewModel() {

    private val _state = MutableStateFlow(CartState())
    val state: StateFlow<CartState> = _state.asStateFlow()

    init {
        val userId = savedStateHandle.get<String>("userId")
        if (!userId.isNullOrBlank()) {
            viewModelScope.launch {
                _state.update { it.copy(isLoading = true, userId = userId) }
                getUser(userId)
                _state.update { it.copy(isLoading = false) }
            }
        }
    }

    private suspend fun getUser(userId: String) {
        val user = firestoreRepository.getUser(userId)
        if (user != null) {
            _state.update { it.copy(userId = userId, user = user) }
        }
    }
}