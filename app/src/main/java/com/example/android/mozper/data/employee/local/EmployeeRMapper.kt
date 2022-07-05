package com.example.android.mozper.data.employee.local

import com.example.android.mozper.data.base.BaseBiMapper
import com.example.android.mozper.domain.employee.Employee
import javax.inject.Inject

class EmployeeRMapper @Inject constructor() : BaseBiMapper<Employee, EmployeeR>() {
    override fun transformDtoL(entityD: Employee?): EmployeeR? {
        return if (entityD?.id == null ||
            entityD.firstName.isBlank()
        ) null
        else EmployeeR(
            entityD.id,
            entityD.firstName,
            entityD.lastName,
            entityD.image,
            entityD.description,
            entityD.rating
        )
    }

    override fun transformLtoD(entityR: EmployeeR?): Employee? {
        return if (entityR == null) null
        else Employee(
            entityR.id,
            entityR.firstName,
            entityR.lastName,
            entityR.image,
            entityR.description,
            entityR.rating
        )
    }

}