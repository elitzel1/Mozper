package com.example.android.mozper.data.sharedpreferences

interface PreferenceDataSource {

    fun <T> savePreference(preferenceKey: String, value: T)

    fun getBooleanPreference(preferenceKey: String, defaultValue: Boolean): Boolean
}