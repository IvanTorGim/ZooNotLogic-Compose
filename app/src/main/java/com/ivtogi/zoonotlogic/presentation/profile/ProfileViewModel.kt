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
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
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

    fun changeName(name: String) {
        _state.update { it.copy(user = _state.value.user.copy(name = name)) }
    }

    private fun validateName(): Boolean {
        _state.update { it.copy(nameError = _state.value.user.name.length < 4) }
        return !_state.value.nameError
    }

    fun changeLastName(lastName: String) {
        _state.update { it.copy(user = _state.value.user.copy(lastName = lastName)) }
    }

    fun changePhone(phone: String) {
        _state.update { it.copy(user = _state.value.user.copy(phone = phone)) }
    }

    fun formatDate(date: Long): String {
        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return sdf.format(Date(date))
    }
}