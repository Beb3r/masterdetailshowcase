package com.gromo.masterdetailshowcase.core.common

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.SharedPreferences

fun Context.sharedPreferences(
    name: String,
    mode: Int = Context.MODE_PRIVATE
): Lazy<SharedPreferences> = lazy {
    getSharedPreferences(name, mode)
}
