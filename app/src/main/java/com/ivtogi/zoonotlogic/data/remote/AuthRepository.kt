package com.ivtogi.zoonotlogic.data.remote

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) {
    suspend fun login(email: String, password: String): FirebaseUser? {
        return firebaseAuth.signInWithEmailAndPassword(email, password).await().user
    }

    suspend fun signup(email: String, password: String): FirebaseUser? {
        return firebaseAuth.createUserWithEmailAndPassword(email, password).await().user
    }

    fun getSession(): String? {
        return firebaseAuth.currentUser?.uid
    }

    fun logout() {
        return firebaseAuth.signOut()
    }
}