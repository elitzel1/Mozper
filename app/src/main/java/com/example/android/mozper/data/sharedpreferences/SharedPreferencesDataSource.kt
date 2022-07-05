package com.example.android.mozper.data.sharedpreferences

import android.content.Context

class SharedPreferencesDataSource constructor(val context: Context) : PreferenceDataSource {
    override fun <T> savePreference(preferenceKey: String, value: T) {
        val sharedPreferences = context.getSharedPreferences(MAIN_SHARED_PREFERENCE,
        Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            when(value) {
                is Boolean -> putBoolean(preferenceKey, value)
                is String -> putString(preferenceKey, value)
                is Int -> putInt(preferenceKey, value)
            }
            commit()
        }
    }

    override fun getBooleanPreference(preferenceKey: String, defaultValue: Boolean): Boolean {
        val sharedPreferences =
            context.getSharedPreferences(MAIN_SHARED_PREFERENCE, Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean(preferenceKey, defaultValue)
    }

    companion object {
        private const val MAIN_SHARED_PREFERENCE = "main.preferenceFile"
    }
}