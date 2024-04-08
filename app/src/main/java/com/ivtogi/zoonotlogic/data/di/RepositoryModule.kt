package com.ivtogi.zoonotlogic.data.di

import com.ivtogi.zoonotlogic.data.remote.AuthService
import com.ivtogi.zoonotlogic.data.remote.FirestoreService
import com.ivtogi.zoonotlogic.data.repository.LoginRepositoryImpl
import com.ivtogi.zoonotlogic.data.repository.ProductRepositoryImpl
import com.ivtogi.zoonotlogic.domain.repository.LoginRepository
import com.ivtogi.zoonotlogic.domain.repository.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideProductRepository(firestoreService: FirestoreService): ProductRepository {
        return ProductRepositoryImpl(firestoreService)
    }

    @Singleton
    @Provides
    fun provideLoginRepository(authService: AuthService): LoginRepository {
        return LoginRepositoryImpl(authService)
    }

}