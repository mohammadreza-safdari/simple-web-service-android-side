package com.example.data.preferences

import android.content.Context
import androidx.core.content.edit

class TokenManager(context: Context) {
    private val prefs = context.getSharedPreferences(
        "auth-prefs",
        Context.MODE_PRIVATE
    )
    companion object {
        private const val TOKEN = "token"
    }
    fun saveToken(token: String) {
        prefs.edit { putString(TOKEN, token) }
    }

    fun getToken(): String? {
        return prefs.getString(TOKEN, null)
    }

    fun clearToken() {
        prefs.edit { remove(TOKEN) }
    }

    fun isLoggedIn(): Boolean {
        return !getToken().isNullOrEmpty()
    }
}