package com.example.android.mozper.domain.logout

import com.example.android.mozper.data.employee.EmployeeRepository
import com.example.android.mozper.data.sharedpreferences.SharedPreferencesGateway
import com.example.android.mozper.presentation.utils.AppPreferenceKeys
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val gateway: SharedPreferencesGateway,
    private val repository: EmployeeRepository
) {

    suspend fun run() = withContext(Dispatchers.IO) {
        repository.clearLocal()
        gateway.savePreference(AppPreferenceKeys.HAS_SESSION, false)
    }
}