package com.android.core.utility

fun String?.orEmpty(): String = this ?: ""

fun String.isEmail(): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

fun Int?.orZero(): Int = this ?: 0

fun Double?.orZero(): Double = this ?: 0.0

fun <T> List<T>?.orEmpty() :List<T> = this ?: emptyList()