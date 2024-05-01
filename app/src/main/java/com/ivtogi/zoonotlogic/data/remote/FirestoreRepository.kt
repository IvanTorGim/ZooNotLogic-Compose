package com.ivtogi.zoonotlogic.data.remote

import com.google.firebase.firestore.FirebaseFirestore
import com.ivtogi.zoonotlogic.data.mapper.toDto
import com.ivtogi.zoonotlogic.data.remote.dto.ProductDto
import com.ivtogi.zoonotlogic.data.remote.dto.UserDto
import com.ivtogi.zoonotlogic.domain.model.User
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


class FirestoreRepository @Inject constructor(
    private val firestore: FirebaseFirestore
) {
    private companion object {
        const val PRODUCTS_PATH = "products"
        const val USER_PATH = "users"
    }

    suspend fun getAllProducts(): List<ProductDto> {
        return firestore.collection(PRODUCTS_PATH).get().await()
            .mapNotNull { document ->
                document.toObject(ProductDto::class.java).copy(id = document.id)
            }
    }

    suspend fun getProduct(productId: String): ProductDto? {
        return firestore.collection(PRODUCTS_PATH)
            .document(productId).get().await()
            .toObject(ProductDto::class.java)
    }

    fun insertUser(id: String, user: User) {
        firestore.collection(USER_PATH).document(id).set(user.toDto())
    }

    suspend fun getUser(userId: String): UserDto? {
        return firestore.collection(USER_PATH)
            .document(userId).get().await()
            .toObject(UserDto::class.java)
    }


}