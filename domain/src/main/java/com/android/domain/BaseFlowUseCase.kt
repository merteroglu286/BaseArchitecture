package com.android.domain

import com.android.domain.model.DataState
import com.android.domain.util.ErrorPopUpType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*


@ExperimentalCoroutinesApi
abstract class BaseFlowUseCase<in In, Out> {

    private val TAG = "BaseFlowUseCase"

    open fun showLoading(): Boolean {
        return true
    }

    open fun errorPopupType(): ErrorPopUpType {
        return ErrorPopUpType.ERROR
    }

    protected abstract fun run(request: In): Flow<DataState<Out>>

    open fun execute(request: In) = run(request)
        .onStart {
            if (showLoading()) {
                emit(DataState.Loading(true))
            }
        }.onCompletion {
            emit(DataState.Loading(false))
        }
        .catch { t ->
            emit(DataState.Error(101, t.localizedMessage.orEmpty(), errorPopupType()))
        }.flowOn(Dispatchers.IO)

    inline fun <reified T> Flow<T>.setSuccess(): DataState.Success<Flow<T>> {
        return DataState.Success(this)
    }
}

