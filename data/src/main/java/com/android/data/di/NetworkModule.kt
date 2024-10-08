package com.android.data.di

import android.content.Context
import com.android.core.utility.orEmpty
import com.android.data.BuildConfig
import com.android.data.api.UserApi
import com.android.data.local.PrefHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    const val TIME_OUT = 300000L

    @Singleton
    @Provides
    fun provideOkhttpClient(
        @ApplicationContext context: Context,
        prefHelper: PrefHelper
    ): OkHttpClient {

        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(Interceptor { chain ->
                val request: Request =
                    chain.request().newBuilder()
                        .addHeader(
                            "Authorization",
                            "Bearer " + prefHelper.getAuthorizationToken().orEmpty()
                        )
                        .addHeader("Content-Type", "application/json")
                        .build()

                chain.proceed(request)
            })
            .build()
    }

    private val interceptor = run {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.apply {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val gson = GsonConverterFactory.create()
        return Retrofit.Builder().client(okHttpClient).baseUrl(BuildConfig.API_URL)
            .addConverterFactory(gson).build()
    }

    @Singleton
    @Provides
    fun provideAccountApi(retrofit: Retrofit): UserApi {
        return retrofit.create(UserApi::class.java)
    }
}