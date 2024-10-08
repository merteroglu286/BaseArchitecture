package com.android.data.local

import android.content.Context
import android.content.SharedPreferences
import com.android.core.utility.orEmpty
import java.util.Locale
import javax.inject.Inject

class PrefHelperImpl @Inject constructor(context: Context) : PrefHelper {

    private val AUTHORIZATON_PREF_HELPER = "authorizationPref"
    private val KEY_USER_ID = "key_user_id"
    private val KEY_LANGUAGE = "key_language"


    lateinit var mPrefs: SharedPreferences

    init {
        mPrefs = context.getSharedPreferences("Pref", Context.MODE_PRIVATE)
    }


    override fun saveAuthorizationToken(authorizationToken: String) {
        mPrefs.edit().putString(AUTHORIZATON_PREF_HELPER, authorizationToken).apply()
    }

    override fun getAuthorizationToken(): String {
        return mPrefs.getString(AUTHORIZATON_PREF_HELPER, "").orEmpty()
    }

    override fun saveUserId(id: Int) {
        mPrefs.edit().putInt(KEY_USER_ID, id).apply()
    }

    override fun getUserId(): Int {
        return mPrefs.getInt(KEY_USER_ID, -1)
    }


    override fun saveLanguage(language: String) {
        mPrefs.edit().putString(KEY_LANGUAGE, language).apply()
    }

    override fun getLanguage(): String {
        return mPrefs.getString(KEY_LANGUAGE, Locale.getDefault().language)
            ?: Locale.getDefault().language
    }

    override fun isLogin(): Boolean {
        return getAuthorizationToken().isNotEmpty()
    }

    override fun logout() {
        saveAuthorizationToken("")
        saveUserId(-1)
    }
}