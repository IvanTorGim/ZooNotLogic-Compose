package com.ivtogi.zoonotlogic.ui.login

data class LoginState(
    val email: String = "ivan_chella@hotmail.com",
    val emailError: Boolean = false,
    val password: String = "ivantg",
    val passwordError: Boolean = false,
    val loginLoading: Boolean = false,
    val loginWithGoogleLoading: Boolean = false,
    val loginError: Boolean = false
)
