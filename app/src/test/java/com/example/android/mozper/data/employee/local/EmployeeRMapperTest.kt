package com.example.android.mozper.data.employee.local

import com.example.android.mozper.domain.employee.Employee
import com.google.common.truth.Truth
import org.junit.Test

class EmployeeRMapperTest {
    private val id = 1
    private val name = "John"
    private val lastName = "Smith"
    private val description = "Something here"
    private val image = "image.com"
    private val rate = 4.5
    private val mapper = EmployeeRMapper()

    @Test
    fun transformSuccessDtoL() {
        //Given
        val entity = Employee(
            id,
            name,
            lastName,
            image,
            description,
            rate
        )

        //When
        val result = mapper.transformDtoL(entity)

        Truth.assertThat(result).isNotNull()
        Truth.assertThat(result?.id).isEqualTo(id)
        Truth.assertThat(result?.firstName).isEqualTo(name)
        Truth.assertThat(result?.lastName).isEqualTo(lastName)
        Truth.assertThat(result?.image).isEqualTo(image)
        Truth.assertThat(result?.rating).isEqualTo(rate)
    }

    @Test
    fun transformSuccessMissingLastNameDtoL() {
        //Given
        val entity = Employee(
            id,
            name,
            "",
            image,
            description,
            rate
        )

        //When
        val result = mapper.transformDtoL(entity)

        Truth.assertThat(result).isNotNull()
        Truth.assertThat(result?.lastName).isEqualTo("")
    }

    @Test
    fun transformSuccessMissingImageDtoL() {
        //Given
        val entity = Employee(
            id,
            name,
            lastName,
            "",
            description,
            rate
        )

        //When
        val result = mapper.transformDtoL(entity)

        Truth.assertThat(result).isNotNull()
        Truth.assertThat(result?.image).isEqualTo("")
    }

    @Test
    fun transformSuccessMissingDescDtoL() {
        //Given
        val entity = Employee(
            id,
            name,
            lastName,
            image,
            "",
            rate
        )

        //When
        val result = mapper.transformDtoL(entity)

        Truth.assertThat(result).isNotNull()
        Truth.assertThat(result?.description).isEqualTo("")
    }

    @Test
    fun transformCorruptedDataNameDtoL() {
        //Given
        val entity = Employee(
            id,
            "",
            lastName,
            image,
            description,
            rate
        )

        //When
        val result = mapper.transformDtoL(entity)

        Truth.assertThat(result).isNull()
    }

    @Test
    fun transformCorruptedDataDtoL() {
        //Given
        val entity : Employee? = null

        //When
        val result = mapper.transformDtoL(entity)

        Truth.assertThat(result).isNull()
    }

    @Test
    fun transformSuccessLtoD() {
        //Given
        val entity = EmployeeR(
            id,
            name,
            lastName,
            image,
            description,
            rate
        )

        //When
        val result = mapper.transformLtoD(entity)

        Truth.assertThat(result).isNotNull()
        Truth.assertThat(result?.id).isEqualTo(id)
        Truth.assertThat(result?.firstName).isEqualTo(name)
        Truth.assertThat(result?.lastName).isEqualTo(lastName)
        Truth.assertThat(result?.image).isEqualTo(image)
        Truth.assertThat(result?.rating).isEqualTo(rate)
    }

    @Test
    fun transformCorruptedDataLtoD() {
        //Given
        val entity : EmployeeR? = null

        //When
        val result = mapper.transformLtoD(entity)

        Truth.assertThat(result).isNull()
    }

}
