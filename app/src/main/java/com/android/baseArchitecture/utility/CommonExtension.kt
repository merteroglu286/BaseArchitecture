package com.android.baseArchitecture.utility

import android.content.Context
import android.content.Intent
import android.net.Uri


fun Context.callPhone(phoneNumber: String) {
    val intent = Intent(Intent.ACTION_DIAL)
    intent.data = Uri.parse("tel:$phoneNumber")
    startActivity(intent)
}
