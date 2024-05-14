package com.ivtogi.zoonotlogic.data.remote

import com.google.firebase.firestore.FirebaseFirestore
import com.ivtogi.zoonotlogic.data.mapper.toDomain
import com.ivtogi.zoonotlogic.data.mapper.toDto
import com.ivtogi.zoonotlogic.data.remote.dto.ProductDto
import com.ivtogi.zoonotlogic.data.remote.dto.UserDto
import com.ivtogi.zoonotlogic.domain.model.CartProduct
import com.ivtogi.zoonotlogic.domain.model.Product
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

    suspend fun getAllProducts(): List<Product> {
        return firestore.collection(PRODUCTS_PATH).get().await()
            .mapNotNull { document ->
                document.toObject(ProductDto::class.java).toDomain().copy(id = document.id)
            }
    }

    suspend fun getProduct(productId: String): Product? {
        return firestore.collection(PRODUCTS_PATH)
            .document(productId).get().await()
            .toObject(ProductDto::class.java)?.toDomain()?.copy(id = productId)
    }

    fun insertProduct(product: Product) {
        if (product.id.isNotEmpty()) {
            firestore.collection(PRODUCTS_PATH).document(product.id).set(product.toDto())
        } else {
            firestore.collection(PRODUCTS_PATH).add(product.toDto())
        }
    }

    fun deleteProduct(productId: String) {
        firestore.collection(PRODUCTS_PATH).document(productId).delete()
    }

    fun insertUser(userId: String, user: User) {
        firestore.collection(USER_PATH).document(userId).set(user.toDto())
    }

    suspend fun getUser(userId: String): User? {
        return firestore.collection(USER_PATH)
            .document(userId).get().await().toObject(UserDto::class.java)?.toDomain()
    }

    fun updateCart(userId: String, cartList: List<CartProduct>) {
        val cartListDto = cartList.map { it.toDto() }
        firestore.collection(USER_PATH).document(userId).update(
            mapOf("cart" to cartListDto)
        )
    }
}