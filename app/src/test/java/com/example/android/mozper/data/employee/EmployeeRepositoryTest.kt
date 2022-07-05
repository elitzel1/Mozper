package com.example.android.mozper.data.employee

import com.example.android.mozper.data.employee.local.EmployeeLocalSource
import com.example.android.mozper.data.employee.remote.EmployeeRemoteSource
import com.example.android.mozper.domain.employee.Employee
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyZeroInteractions
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

@ExperimentalCoroutinesApi
class EmployeeRepositoryTest {

    private val id = 1
    private val name = "John"
    private val lastName = "Smith"
    private val description = "Something here"
    private val image = "image.com"
    private val rate = 4.5

    private val localSource = mock<EmployeeLocalSource>()
    private val remoteSource = mock<EmployeeRemoteSource>()

    private val testObject = EmployeeRepository(remoteSource, localSource)

    @Test
    fun getListWhenLocalIsEmpty() = runBlockingTest {

        //Given
        val localData: List<Employee> = listOf()
        whenever(localSource.getAll()).thenReturn(localData)

        //When
        testObject.get()

        //Then
        verify(remoteSource).fetchEmployees()

    }

    @Test
    fun getLocalListWhenLocalIsNotEmpty() = runBlockingTest {

        //Given
        val localData: List<Employee> = listOf(
            Employee(
                id,
                name,
                lastName,
                description,
                image,
                rate
            )
        )
        whenever(localSource.getAll()).thenReturn(localData)

        //When
        testObject.get()

        //Then
        verifyZeroInteractions(remoteSource)

    }
}
