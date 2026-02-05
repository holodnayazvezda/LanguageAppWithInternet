package com.example.languageapp.data.storage

import android.content.Context
import androidx.core.content.edit

open class Storage(
    context: Context,
    sharedPreferencesName: String,
) {
    private val sharedPreferences = context.getSharedPreferences(
        sharedPreferencesName,
        Context.MODE_PRIVATE
    )

    fun getString(key: String): String? {
        return sharedPreferences.getString(key, "")
    }

    fun putString(key: String, value: String?) {
        sharedPreferences.edit { putString(key, value) }
    }

    fun getInteger(key: String): Int {
        return sharedPreferences.getInt(key, 0)
    }

    fun putInteger(key: String, value: Int) {
        sharedPreferences.edit { putInt(key, value) }
    }

    fun getBoolean(key: String): Boolean {
        return sharedPreferences.getBoolean(key, false)
    }

    fun putBoolean(key: String, value: Boolean) {
        sharedPreferences.edit {
            putBoolean(key, value)
        }
    }
}