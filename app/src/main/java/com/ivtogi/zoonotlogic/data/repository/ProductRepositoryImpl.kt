package com.ivtogi.zoonotlogic.data.repository

import com.ivtogi.zoonotlogic.data.mapper.toDomain
import com.ivtogi.zoonotlogic.data.remote.FirestoreService
import com.ivtogi.zoonotlogic.domain.model.Product
import com.ivtogi.zoonotlogic.domain.repository.ProductRepository
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val firestoreService: FirestoreService
) : ProductRepository {

    override suspend fun getAllProducts(): List<Product> {
        return firestoreService.getAllProducts().map { it.toDomain() }
    }

    override suspend fun getNewCollection(): List<Product> {
        TODO("Not yet implemented")
    }
}