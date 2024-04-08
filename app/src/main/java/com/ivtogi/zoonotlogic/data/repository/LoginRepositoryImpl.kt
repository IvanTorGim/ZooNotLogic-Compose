package com.ivtogi.zoonotlogic.data.repository

import com.ivtogi.zoonotlogic.data.remote.AuthService
import com.ivtogi.zoonotlogic.domain.repository.LoginRepository
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val authService: AuthService
): LoginRepository {
    override suspend fun login(email: String, password: String) {
        authService.login(email, password)
    }
}