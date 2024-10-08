package com.android.domain.repository

import com.android.domain.model.DataState
import com.android.domain.model.user.UserUiModel

interface UsersRepository {
    suspend fun getUsers(id:Int): DataState<List<UserUiModel>>
}