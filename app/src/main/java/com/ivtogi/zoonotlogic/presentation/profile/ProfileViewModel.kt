package com.ivtogi.zoonotlogic.presentation.profile

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
class ProfileViewModel @Inject constructor(
    saveStateHandle: SavedStateHandle,
    private val firestoreRepository: FirestoreRepository
) : ViewModel() {

    private val _state = MutableStateFlow(ProfileState())
    val state: StateFlow<ProfileState> = _state.asStateFlow()

    init {
        saveStateHandle.get<String>("userId")?.let { userId ->
            viewModelScope.launch {
                _state.update { it.copy(isLoading = true, userId = userId) }
                val user = firestoreRepository.getUser(userId)
                val orders = firestoreRepository.getUserOrders(userId)
                _state.update { it.copy(isLoading = false, user = user, orders = orders) }
            }
        }
    }
}