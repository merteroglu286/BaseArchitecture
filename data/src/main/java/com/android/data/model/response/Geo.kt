package com.android.data.model.response


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
@Keep
data class Geo(
    @SerializedName("lat")
    val lat: String?,
    @SerializedName("lng")
    val lng: String?
)