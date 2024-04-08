package com.ivtogi.zoonotlogic.domain.repository

interface LoginRepository {

    suspend fun login(email: String, password: String)
}