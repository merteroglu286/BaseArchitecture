package com.android.data.model.response


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Address(
    @SerializedName("city")
    val city: String?,
    @SerializedName("geo")
    val geo: Geo?,
    @SerializedName("street")
    val street: String?,
    @SerializedName("suite")
    val suite: String?,
    @SerializedName("zipcode")
    val zipcode: String?
)