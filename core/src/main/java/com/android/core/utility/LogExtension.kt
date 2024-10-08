package com.android.core.utility

import android.util.Log
import androidx.fragment.app.Fragment


fun Fragment.dLog(message: String) {
    Log.d(this::class.java.simpleName, message)
}