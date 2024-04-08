package com.ivtogi.zoonotlogic.domain.repository

import com.ivtogi.zoonotlogic.domain.model.Product

interface ProductRepository {
    suspend fun getAllProducts(): List<Product>
    suspend fun getNewCollection(): List<Product>
}