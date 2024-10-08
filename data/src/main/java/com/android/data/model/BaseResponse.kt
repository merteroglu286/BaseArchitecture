package com.android.data.model

import androidx.annotation.Keep
import com.android.domain.model.DataState
import com.google.gson.annotations.SerializedName
@Keep
data class BaseResponse<T>(
    @SerializedName("responseCode")
    val responseCode: Int,
    @SerializedName("data")
    val data: T?,
    @SerializedName("responseMessage")
    val responseMessage: String?
)


fun <T, K> BaseResponse<T>.handle(map: K): DataState<K?> {
    return if (this.responseCode == 200) {
        DataState.Success(map)
    } else {
        DataState.Error(this.responseCode, this.responseMessage ?: "")
    }
}

fun <T> BaseResponse<T>.isSuccess(): Boolean = this.responseCode == 200