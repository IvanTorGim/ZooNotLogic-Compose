package com.ivtogi.zoonotlogic.presentation.signup

data class SignupState(
    val email: String = "",
    val emailError: Boolean = false,
    val password: String = "",
    val passwordError: Boolean = false,
    val name: String = "",
    val nameError: Boolean = false,
    val lastName: String = "",
    val lastNameError: Boolean = false,
    val phone: String = "",
    val phoneError: Boolean = false,
    val signupLoading: Boolean = false,
    val signupError: String? = null
)
