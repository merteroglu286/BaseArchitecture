package com.android.data.api

import com.android.data.model.response.UserResponse
import retrofit2.http.GET

interface UserApi {
    @GET("users")
    suspend fun getUsers(): List<UserResponse>
}