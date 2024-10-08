package com.android.data.di

import android.content.Context
import android.location.Geocoder
import com.android.core.utility.PermissionManager
import com.android.data.repositories.LocationServiceImpl
import com.android.domain.repository.LocationService
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocationModule {

    @Singleton
    @Provides
    fun provideFusedLocationClient(@ApplicationContext context: Context): FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(context)
    }

    @Provides
    @Singleton
    fun provideLocationService(
        fusedLocationProviderClient: FusedLocationProviderClient,
        geocoder: Geocoder,
        permissionManager: PermissionManager
    ): LocationService {
        return LocationServiceImpl(fusedLocationProviderClient, geocoder, permissionManager)
    }

    @Provides
    @Singleton
    fun provideGeocoder(@ApplicationContext context: Context): Geocoder {
        return Geocoder(context)
    }
}

