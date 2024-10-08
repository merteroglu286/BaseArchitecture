package com.android.data.repositories


import com.android.data.api.UserApi
import com.android.data.mapper.toUserUiModel
import com.android.domain.model.DataState
import com.android.domain.model.user.UserUiModel
import com.android.domain.repository.UsersRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject


@ExperimentalCoroutinesApi
class UsersRepositoryImpl @Inject constructor(
    private val userApi: UserApi
) : UsersRepository {

    override suspend fun getUsers(id: Int): DataState<List<UserUiModel>> {
        return DataState.Success(userApi.getUsers().map { user ->
            user.toUserUiModel()
        })
    }

}