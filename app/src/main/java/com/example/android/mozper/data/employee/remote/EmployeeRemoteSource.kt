package com.example.android.mozper.data.employee.remote

import com.example.android.mozper.domain.employee.Employee

interface EmployeeRemoteSource {

    suspend fun fetchEmployees(): List<Employee>
}