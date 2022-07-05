package com.example.android.mozper.data.employee.remote

import com.example.android.mozper.data.base.BaseMapper
import com.example.android.mozper.domain.employee.Employee
import javax.inject.Inject

class EmployeeMapperResponse @Inject constructor() : BaseMapper<EmployeeResponse, Employee>() {

    override fun transform(input: EmployeeResponse?): Employee? {
        return if (input?.id == null ||
            input.firstName == null ||
            input.firstName == "" ||
            input.rating == null
        ) null
        else Employee(
            input.id,
            input.firstName,
            input.lastName ?: "-",
            input.image ?: "",
            input.description ?: "-",
            input.rating
        )
    }
}