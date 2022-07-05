package com.example.android.mozper.data.employee.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface EmployeeDao {
    @Query("SELECT * FROM employee_table")
    suspend fun getAllEmployees(): List<EmployeeR>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(employees: List<EmployeeR>)

    @Query("DELETE FROM employee_table")
    suspend fun deleteAll()
}