package com.android.data.model.response


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
@Keep
data class Company(
    @SerializedName("bs")
    val bs: String?,
    @SerializedName("catchPhrase")
    val catchPhrase: String?,
    @SerializedName("name")
    val name: String?
)