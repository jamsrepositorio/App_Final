package com.example.app_final.data

import android.content.Context
import com.example.app_final.R

object UserPreferences {
    fun saveCredential(context: Context, email: String) {
        val prefs = context.getSharedPreferences(
            context.getString(R.string.prefs_file),
            Context.MODE_PRIVATE
        ).edit()
        prefs.putString("email", email)
        prefs.apply()
    }

    fun getCredential(context: Context): String {
        val prefs = context.getSharedPreferences(
            context.getString(R.string.prefs_file),
            Context.MODE_PRIVATE
        )
        return prefs.getString("email", "").toString()
    }
}