package com.example.authpaymentapplication.app.setting

import android.content.Context

class SharedPrefSettings(context: Context) : AppSettings {
    private val sharedPreferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE)

    override fun getCurrentToken(): String? =
        sharedPreferences.getString(SHARED_PREF_CURRENT_TOKEN, null)

    override fun setCurrentToken(token: String?) {
        val editor = sharedPreferences.edit()
        if (token == null)
            editor.remove(SHARED_PREF_CURRENT_TOKEN)
        else
            editor.putString(SHARED_PREF_CURRENT_TOKEN, token)
        editor.apply()
    }

    companion object {
        const val SHARED_PREF_CURRENT_TOKEN = "TOKEN"
    }
}