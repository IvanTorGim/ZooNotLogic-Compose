package com.ivtogi.zoonotlogic.data.remote

import com.google.firebase.firestore.FirebaseFirestore
import com.ivtogi.zoonotlogic.data.remote.dto.ProductResponse
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


class FirestoreService @Inject constructor(
    private val firestore: FirebaseFirestore
) {

    private companion object {
        const val PRODUCTS_PATH = "products"
    }
    suspend fun getAllProducts(): List<ProductResponse> {
        return firestore.collection(PRODUCTS_PATH).get().await()
            .map { it.toObject(ProductResponse::class.java) }
    }


}