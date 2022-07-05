package com.example.android.mozper.data.employee.local

import com.example.android.mozper.domain.employee.Employee

class EmployeeRoomSource(
    private val dao: EmployeeDao,
    private val mapper: EmployeeRMapper
) : EmployeeLocalSource {
    override suspend fun insertAll(employees: List<Employee>): Boolean {
        val entities = mapper.transformDtoL(employees) ?: return false
        dao.insertAll(entities)
        return true
    }

    override suspend fun getAll(): List<Employee> {
        return mapper.transformLtoD(dao.getAllEmployees())
    }

    override suspend fun clear(): Boolean {
        dao.deleteAll()
        return true
    }


}