package com.example.android.mozper.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.android.mozper.data.employee.local.EmployeeDao
import com.example.android.mozper.data.employee.local.EmployeeR

@Database(entities = [EmployeeR::class], version = 1)
abstract class MozperDatabase : RoomDatabase() {
    abstract fun getEmployeeDao(): EmployeeDao
}
