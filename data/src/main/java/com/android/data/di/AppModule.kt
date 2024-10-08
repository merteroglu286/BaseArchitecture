package com.android.data.di

import android.content.Context
import com.android.data.local.PrefHelper
import com.android.data.local.PrefHelperImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providePrefHelper(@ApplicationContext context: Context): PrefHelper {
        return PrefHelperImpl(context)
    }
}