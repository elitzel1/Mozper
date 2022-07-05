package com.example.android.mozper.domain.login

import com.example.android.mozper.data.sharedpreferences.SharedPreferencesGateway
import com.example.android.mozper.presentation.utils.AppPreferenceKeys
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HasSessionActiveUseCase @Inject constructor(private val gateway: SharedPreferencesGateway) {

    suspend fun run() = withContext(Dispatchers.IO) {
        gateway.getBooleanPreference(AppPreferenceKeys.HAS_SESSION, false)
    }
}