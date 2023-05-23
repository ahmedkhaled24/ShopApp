package com.hamtary.shopapp.di

import android.content.Context
import com.hamtary.shopapp.data.local.DataStoreManager
import com.hamtary.shopapp.data.network.ApiService
import com.hamtary.shopapp.repository.AuthRepository
import com.hamtary.shopapp.repository.AuthRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAuthRepo(apiService: ApiService): AuthRepository =
        AuthRepositoryImpl(apiService)

    @Provides
    @Singleton
    fun dataStoreManager(@ApplicationContext context: Context): DataStoreManager =
        DataStoreManager(context)

    @Singleton
    @Provides
    fun provideApplicationContext(
        @ApplicationContext context: Context
    ) = context

}