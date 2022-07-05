package com.example.android.mozper.di.data

import com.example.android.mozper.data.api.MozperApi
import com.example.android.mozper.data.database.MozperDatabase
import com.example.android.mozper.data.employee.local.EmployeeDao
import com.example.android.mozper.data.employee.local.EmployeeLocalSource
import com.example.android.mozper.data.employee.local.EmployeeRMapper
import com.example.android.mozper.data.employee.local.EmployeeRoomSource
import com.example.android.mozper.data.employee.remote.EmployeeMapperResponse
import com.example.android.mozper.data.employee.remote.EmployeeRemoteSource
import com.example.android.mozper.data.employee.remote.EmployeeRetrofitSource
import dagger.Module
import dagger.Provides
import dagger.Reusable
import javax.inject.Singleton

@Module
class EmployeeModule {

    @Singleton
    @Provides
    fun provideEmployeeDao(mozperDatabase: MozperDatabase) = mozperDatabase.getEmployeeDao()

    @Provides
    fun provideEmployeeRoomSource(
        dao: EmployeeDao,
        mapper: EmployeeRMapper
    ): EmployeeLocalSource = EmployeeRoomSource(dao, mapper)

    @Reusable
    @Provides
    fun provideEmployeeRetrofitSource(
        api: MozperApi,
        mapper: EmployeeMapperResponse
    ): EmployeeRemoteSource = EmployeeRetrofitSource(api, mapper)
}