package com.ivtogi.zoonotlogic.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Singleton
    @Provides
    fun provideRetrofitCloudFunctions(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://us-central1-zoo-not-logic.cloudfunctions.net/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}