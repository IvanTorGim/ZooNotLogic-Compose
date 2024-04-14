package com.ivtogi.zoonotlogic.presentation.home

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
    private val firestoreRepository: FirestoreRepository
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state.asStateFlow()

    init {
        getAllProducts()
    }

    private fun getAllProducts() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    productList = firestoreRepository.getAllProducts()
                        .map { product -> product.toDomain() }
                )
            }
        }
    }
}