package com.ivtogi.zoonotlogic.ui.login

import android.util.Patterns
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ivtogi.zoonotlogic.data.remote.AuthService
import com.ivtogi.zoonotlogic.domain.repository.LoginRepository
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
    private val loginRepository: LoginRepository
) : ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state: StateFlow<LoginState> = _state.asStateFlow()

    fun login(navigateToMain: () -> Unit) {
        if (validateEmail() && validatePassword()) {
            viewModelScope.launch {
                try {
                    _state.update { it.copy(loginLoading = true) }
                    val result = loginRepository.login(_state.value.email, _state.value.password)
                    if (result != null) {
                        navigateToMain()
                    }

                } catch (e: Exception) {
                    _state.update { it.copy(loginError = true) }
                }
                delay(2000)
                _state.update { it.copy(loginLoading = false) }
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
        _state.update { it.copy(loginError = false) }
    }
}