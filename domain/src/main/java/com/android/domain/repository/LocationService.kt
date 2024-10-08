package com.android.domain.repository

import android.location.Address
import android.location.Location
import kotlinx.coroutines.flow.Flow

interface LocationService {
    fun getLocation(): Flow<Location?>
    fun locationToString(latitude: Double, longitude: Double): Flow<Address?>
}