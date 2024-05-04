package com.ivtogi.zoonotlogic.presentation.login

data class LoginState(
    val email: String = "ivan_chella@hotmail.com",
    val emailError: Boolean = false,
    val password: String = "ivantg",
    val passwordError: Boolean = false,
    val isLoading: Boolean = false,
    val loginError: String? = null
)
