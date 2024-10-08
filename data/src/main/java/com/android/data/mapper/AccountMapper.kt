package com.android.data.mapper

import com.android.core.utility.orEmpty
import com.android.core.utility.orZero
import com.android.data.model.response.UserResponse
import com.android.domain.model.user.UserUiModel


fun UserResponse.toUserUiModel() = UserUiModel(
    id = id.orZero(),
    name = name.orEmpty(),
    email = email.orEmpty()
)