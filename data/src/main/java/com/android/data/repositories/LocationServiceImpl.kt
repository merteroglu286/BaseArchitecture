package com.android.data.repositories

import android.location.Address
import android.location.Geocoder
import android.location.Location
import com.android.core.utility.PermissionManager
import com.android.domain.repository.LocationService
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class LocationServiceImpl @Inject constructor(
    private val locationProviderClient: FusedLocationProviderClient,
    private val geocoder: Geocoder,
    private val permissionManager: PermissionManager
) : LocationService {

    override fun getLocation(): Flow<Location?> = callbackFlow {
        if (permissionManager.checkLocationPermission()) {
            locationProviderClient.lastLocation.addOnSuccessListener { location ->
                launch { send(location) }
            }
        }
        awaitClose()
    }

    override fun locationToString(latitude: Double, longitude: Double): Flow<Address?> = callbackFlow {

        val addresses = geocoder.getFromLocation(latitude, longitude, 1) ?: return@callbackFlow

        if (addresses.isNotEmpty()) {
            launch { send(addresses.first()) }
        } else {
            launch { send(null) }
        }
        awaitClose()
    }
}