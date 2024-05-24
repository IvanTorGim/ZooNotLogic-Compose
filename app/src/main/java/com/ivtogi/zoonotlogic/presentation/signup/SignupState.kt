package com.ivtogi.zoonotlogic.presentation.signup

data class SignupState(
    val email: String = "",
    val emailError: Boolean = false,
    val password: String = "",
    val passwordError: Boolean = false,
    val name: String = "",
    val nameError: Boolean = false,
    val lastName: String = "",
    val phone: String = "",
    val isLoading: Boolean = false,
    val signupError: String? = null
)
