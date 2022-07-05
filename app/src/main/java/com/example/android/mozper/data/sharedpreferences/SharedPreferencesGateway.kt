package com.example.android.mozper.data.sharedpreferences

import dagger.Reusable
import javax.inject.Inject

@Reusable
class SharedPreferencesGateway @Inject constructor(private val source: PreferenceDataSource) {

    fun getBooleanPreference(preferenceKey: String, defaultValue: Boolean = false) =
        source.getBooleanPreference(preferenceKey, defaultValue)

    fun <T> savePreference(preferenceKey: String, value: T) {
        source.savePreference(preferenceKey, value)
    }
}