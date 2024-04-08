package com.ivtogi.zoonotlogic.ui.singup

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow(SignupState())
    val state: StateFlow<SignupState> = _state.asStateFlow()

    fun changeEmail(email: String) {
        _state.update { it.copy(email = email) }
    }

    private fun validateEmail(): Boolean {
        _state.update {
            it.copy(
                emailError = !Patterns.EMAIL_ADDRESS.matcher(_state.value.email).matches()
            )
        }
        return !_state.value.emailError
    }

    fun changePassword(password: String) {
        _state.update { it.copy(password = password) }
    }

    private fun validatePassword(): Boolean {
        _state.update { it.copy(passwordError = _state.value.password.length < 6) }
        return !_state.value.passwordError
    }

    fun changeUsername(name: String) {
        _state.update { it.copy(name = name) }
    }

    private fun validateName(): Boolean {
        _state.update { it.copy(nameError = _state.value.name.length < 4) }
        return !_state.value.nameError
    }

    fun signup() {
        if (validateEmail() && validatePassword() && validateName()) {
            viewModelScope.launch {
                _state.update { it.copy(signupLoading = true) }
                // TODO
                delay(2000)
                _state.update { it.copy(signupLoading = false) }
            }
        }
    }

    fun changePhone(phone: String) {
        _state.update { it.copy(phone = phone) }
    }

}