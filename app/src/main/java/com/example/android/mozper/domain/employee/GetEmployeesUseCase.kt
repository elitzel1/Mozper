package com.example.android.mozper.domain.employee

import com.example.android.mozper.data.employee.EmployeeRepository
import javax.inject.Inject

class GetEmployeesUseCase @Inject constructor(private val repository: EmployeeRepository) {

    suspend fun run(): List<Employee> {
        return repository.get()
    }
}