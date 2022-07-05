package com.example.android.mozper.data.employee.remote

import com.example.android.mozper.data.api.MozperApi
import com.example.android.mozper.domain.employee.Employee

class EmployeeRetrofitSource(
    private val api: MozperApi,
    private val mapper: EmployeeMapperResponse
) : EmployeeRemoteSource {

    override suspend fun fetchEmployees(): List<Employee> {
        val response = api.fetchEmployee()
        val body = response.body()?.employees
            ?: throw Exception("Error in the call")
        return mapper.transform(body) ?: throw Exception("Error in the mapper")

    }

}