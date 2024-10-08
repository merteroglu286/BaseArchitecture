package com.android.baseArchitecture.utility

import java.text.DecimalFormat

fun Double?.toPrice(): String {
    if (this == null) {
        return "€0,00"
    }

    val decimalFormat = DecimalFormat("#,##0.00")
    return "€" + decimalFormat.format(this)
}

fun Double?.prepareForPaypal(): String {
    if (this == null)
        return "0.0"
    return String.format("%.2f", this).replace(",",".")
}