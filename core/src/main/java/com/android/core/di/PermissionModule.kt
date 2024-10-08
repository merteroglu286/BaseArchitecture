package com.android.core.di

import android.content.Context
import com.android.core.utility.PermissionManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PermissionModule {

    @Singleton
    @Provides
    fun providePermissionManager(@ApplicationContext context: Context) =
        PermissionManager(context)
}