package com.example.android.mozper.domain.login

import com.example.android.mozper.data.sharedpreferences.SharedPreferencesGateway
import com.example.android.mozper.presentation.utils.AppPreferenceKeys
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val sharedPreferencesGateway: SharedPreferencesGateway) {

    suspend fun run(user: String?, password: String?): Boolean  {
         if(user.isNullOrBlank() || password.isNullOrBlank()) {
             return false
         }
        sharedPreferencesGateway.savePreference(AppPreferenceKeys.HAS_SESSION, true)
        return true
    }
}