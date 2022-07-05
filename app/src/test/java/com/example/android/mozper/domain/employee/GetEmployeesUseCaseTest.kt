package com.example.android.mozper.domain.employee

import com.example.android.mozper.data.employee.EmployeeRepository
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Test

@ExperimentalCoroutinesApi
class GetEmployeesUseCaseTest {

    private val id = 1
    private val name = "John"
    private val lastName = "Smith"
    private val description = "Something here"
    private val image = "image.com"
    private val rate = 4.5


    private val gateway = mock<EmployeeRepository>()
    private val testObject = GetEmployeesUseCase(gateway)

    @Test
    fun invokeOfEmployees() = runBlocking {
        //Given
        val employees = listOf(
            Employee(
                id,
                name,
                lastName,
                description,
                image,
                rate
            )
        )

        whenever(gateway.get()).thenReturn(employees)

        //WHEN
        val result = testObject.run()

        //THEN
        assertThat(result.size).isEqualTo(1)
        assertThat(result.first().firstName).isEqualTo(name)
    }
}
