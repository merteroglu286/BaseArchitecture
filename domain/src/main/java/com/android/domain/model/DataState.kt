package com.android.domain.model

import androidx.annotation.Keep
import com.android.domain.util.ErrorPopUpType
@Keep
sealed class DataState<T> {
    data class Success<T>(val data: T) : DataState<T>()
    data class Loading<T>(val isLoading: Boolean) : DataState<T>()
    data class Error<T>(
        val errorCode: Int,
        val errorMessage: String,
        val errorPopUpType: ErrorPopUpType = ErrorPopUpType.ERROR
    ) : DataState<T>()
}
