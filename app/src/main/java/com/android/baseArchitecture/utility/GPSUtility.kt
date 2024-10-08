package com.android.baseArchitecture.utility

import android.app.Activity
import android.content.IntentSender
import android.os.Handler
import android.os.Looper
import com.android.baseArchitecture.ui.main.MainActivity
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import kotlinx.coroutines.ExperimentalCoroutinesApi

class GPSUtility {
    companion object {
        @ExperimentalCoroutinesApi
        fun enableLocationSettings(
            activity: Activity, successCallback: (() -> Unit?) = {},
            errorCallback: (() -> Unit?) = {},
            notFoundLocationService: (() -> Unit?) = {}
        ) {
            val locationRequest = LocationRequest.create()
            locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY

            val builder = LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest)


            val task = LocationServices.getSettingsClient(activity)
                .checkLocationSettings(builder.build())

            task.addOnSuccessListener { response ->
                val states = response.locationSettingsStates
                successCallback()
            }
            task.addOnFailureListener { e ->


                if (e is ResolvableApiException) {
                    try {
                        errorCallback()
                        e.startResolutionForResult(
                            activity,
                            AppConstants.LOCATION_SETTING_REQUEST
                        )
                    } catch (sendEx: IntentSender.SendIntentException) {
                        errorCallback()
                    }
                } else {
                    notFoundLocationService()
                }
            }
        }
    }
}

@OptIn(ExperimentalCoroutinesApi::class)
fun Activity?.checkGps(getLocation: () -> Unit, notFoundLocationService: (() -> Unit?) = {}) {
    (this as? MainActivity)?.let { activity ->
        activity.checkGPS(successCallback = {
            getLocation()
        }, errorCallback = {
           Handler(Looper.getMainLooper()).postDelayed({
               activity.gpsListener { getLocation() }
           },200L)
        }, notFoundLocationService = {
            notFoundLocationService.invoke()
        })
    }
}