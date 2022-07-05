package com.example.android.mozper.data.employee.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "employee_table")
data class EmployeeR(
    @PrimaryKey
    val id: Int,
    val firstName: String,
    val lastName: String,
    val image: String,
    val description: String,
    val rating: Double
)