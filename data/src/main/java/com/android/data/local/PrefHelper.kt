package com.android.data.local

interface PrefHelper {

    fun saveAuthorizationToken(authorizationToken: String)

    fun getAuthorizationToken(): String?

    fun saveUserId(id: Int)

    fun getUserId(): Int?

    fun isLogin(): Boolean

    fun saveLanguage(language:String)

    fun getLanguage(): String

    fun logout()
}