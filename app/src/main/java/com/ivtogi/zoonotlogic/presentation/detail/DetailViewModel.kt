package com.ivtogi.zoonotlogic.presentation.detail

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(

) : ViewModel() {
    private val _state = MutableStateFlow(DetailState())
    val state: StateFlow<DetailState> = _state.asStateFlow()

    init {
        _state.update {
            it.copy(
                images = listOf(
                    "https://firebasestorage.googleapis.com/v0/b/zoo-not-logic.appspot.com/o/products%2Ftee_butterfly_brown_0.jpg?alt=media&token=0d1caf5c-32b4-4efd-9203-d77a481f1a26",
                    "https://firebasestorage.googleapis.com/v0/b/zoo-not-logic.appspot.com/o/products%2Ftee_butterfly_brown_1.jpg?alt=media&token=b2481409-c6cc-4c87-828f-467cd9be41ea"
                )
            )
        }
    }

    fun onImageClicked(position: Int) {
        _state.update { it.copy(imageSelected = position) }
    }

}