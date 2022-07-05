package com.example.android.mozper.data.employee.local

import com.example.android.mozper.domain.employee.Employee

interface EmployeeLocalSource {

    suspend fun insertAll(employees: List<Employee>): Boolean
    suspend fun getAll(): List<Employee>
    suspend fun clear(): Boolean
}
