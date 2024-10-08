package com.android.data.di

import com.android.data.repositories.UsersRepositoryImpl
import com.android.domain.repository.UsersRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun provideAccountRepository(usersRepositoryImpl: UsersRepositoryImpl): UsersRepository
}