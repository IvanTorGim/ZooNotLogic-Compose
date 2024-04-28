package com.ivtogi.zoonotlogic.presentation.login

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ivtogi.zoonotlogic.data.remote.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state: StateFlow<LoginState> = _state.asStateFlow()

    fun login(navigateToHome: (String) -> Unit) {
        if (validateEmail() && validatePassword()) {
            viewModelScope.launch {
                try {
                    _state.update { it.copy(loginLoading = true) }
                    val result = authRepository.login(_state.value.email, _state.value.password)
                    if (result != null) {
                        navigateToHome(result.uid)
                    }
                } catch (e: Exception) {
                    _state.update { it.copy(loginError = e.message) }
                } finally {
                    _state.update { it.copy(loginLoading = false) }
                }
            }
        }
    }

    fun loginWithGoogle() {
        viewModelScope.launch {
            _state.update { it.copy(loginWithGoogleLoading = true) }
            delay(3000)
            _state.update { it.copy(loginWithGoogleLoading = false) }
        }
    }

    fun changeEmail(email: String) {
        _state.update { it.copy(email = email) }
    }

    private fun validateEmail(): Boolean {
        _state.update {
            it.copy(
                emailError = !Patterns.EMAIL_ADDRESS.matcher(state.value.email).matches()
            )
        }
        return !_state.value.emailError
    }

    fun changePassword(password: String) {
        _state.update { it.copy(password = password) }
    }

    private fun validatePassword(): Boolean {
        _state.update { it.copy(passwordError = state.value.password.length < 6) }
        return !_state.value.passwordError
    }

    fun cleanLoginError() {
        _state.update { it.copy(loginError = null) }
    }
}