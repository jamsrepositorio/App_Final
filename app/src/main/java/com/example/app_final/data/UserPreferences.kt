package com.example.app_final.data

import android.content.Context
import com.example.app_final.R

object UserPreferences {
    const val EMAIL = "email"
    const val EMPTY_STRING = ""
    const val GOOGLE_CODE = 123

    fun saveCredential(context: Context, email: String) {
        val prefs = context.getSharedPreferences(
            context.getString(R.string.prefs_file),
            Context.MODE_PRIVATE
        ).edit()
        prefs.putString(EMAIL, email)
        prefs.apply()
    }

    fun getCredential(context: Context): String {
        val prefs = context.getSharedPreferences(
            context.getString(R.string.prefs_file),
            Context.MODE_PRIVATE
        )
        return prefs.getString(EMAIL, EMPTY_STRING).toString()
    }
}