package com.example.android.mozper.data.api

import com.example.android.mozper.data.employee.remote.EmployeesResponse
import retrofit2.Response
import retrofit2.http.GET

interface MozperApi {

    @GET("/")
    suspend fun fetchEmployee(): Response<EmployeesResponse>
}