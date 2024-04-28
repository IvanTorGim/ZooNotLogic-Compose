package com.ivtogi.zoonotlogic.data.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.ivtogi.zoonotlogic.data.remote.AuthRepository
import com.ivtogi.zoonotlogic.data.remote.FirestoreRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    @Singleton
    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Singleton
    @Provides
    fun provideAuthRepository(firebaseAuth: FirebaseAuth): AuthRepository = AuthRepository(firebaseAuth)

    @Singleton
    @Provides
    fun provideFirebaseStore(): FirebaseFirestore = Firebase.firestore

    @Singleton
    @Provides
    fun provideFirestoreRepository(firestore: FirebaseFirestore): FirestoreRepository =
        FirestoreRepository(firestore)
}