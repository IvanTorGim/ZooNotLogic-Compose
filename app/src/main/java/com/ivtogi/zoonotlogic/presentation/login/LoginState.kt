package com.ivtogi.zoonotlogic.presentation.login

data class LoginState(
    val userId: String? = null,
    val email: String = "",
    val emailError: Boolean = false,
    val password: String = "",
    val passwordError: Boolean = false,
    val loginError: String? = null
)
