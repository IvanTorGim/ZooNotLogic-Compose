package com.ivtogi.zoonotlogic.presentation.signup

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.ivtogi.zoonotlogic.data.remote.AuthRepository
import com.ivtogi.zoonotlogic.data.remote.FirestoreRepository
import com.ivtogi.zoonotlogic.domain.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val authService: AuthRepository,
    private val firestoreService: FirestoreRepository
) : ViewModel() {
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

    fun changeName(name: String) {
        _state.update { it.copy(name = name) }
    }

    private fun validateName(): Boolean {
        _state.update { it.copy(nameError = _state.value.name.length < 4) }
        return !_state.value.nameError
    }

    fun changeLastName(lastName: String) {
        _state.update { it.copy(lastName = lastName) }
    }

    fun changePhone(phone: String) {
        _state.update { it.copy(phone = phone) }
    }

    fun signup(navigateToLogin: () -> Unit) {
        if (validateEmail() && validatePassword() && validateName()) {
            viewModelScope.launch {
                try {
                    _state.update { it.copy(isLoading = true) }
                    val result: FirebaseUser? = authService.signup(
                        email = _state.value.email,
                        password = _state.value.password,
                    )
                    if (result != null) {
                        firestoreService.insertUser(
                            userId = result.uid,
                            user = User(
                                name = _state.value.name,
                                lastName = _state.value.lastName,
                                phone = _state.value.phone,
                                email = _state.value.email
                            )
                        )
                        navigateToLogin()
                    }
                } catch (e: Exception) {
                    _state.update { it.copy(signupError = e.localizedMessage) }
                } finally {
                    _state.update { it.copy(isLoading = false) }
                }
            }
        }
    }

    fun cleanSignupError() {
        _state.update { it.copy(signupError = null) }
    }


}