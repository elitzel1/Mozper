package com.example.android.mozper.data.employee

import com.example.android.mozper.data.employee.local.EmployeeLocalSource
import com.example.android.mozper.data.employee.remote.EmployeeRemoteSource
import com.example.android.mozper.domain.employee.Employee
import javax.inject.Inject

class EmployeeRepository @Inject constructor(
    private val remote: EmployeeRemoteSource,
    private val local: EmployeeLocalSource
) {

    suspend fun get(): List<Employee> {
        val localData = local.getAll()
        return localData.ifEmpty {
            val remoteData = remote.fetchEmployees()
            local.insertAll(remoteData)
            remoteData
        }
    }

    suspend fun clearLocal(): Boolean {
        return local.clear()
    }
}