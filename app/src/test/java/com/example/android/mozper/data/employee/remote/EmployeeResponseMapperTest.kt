package com.example.android.mozper.data.employee.remote

import com.google.common.truth.Truth
import org.junit.Test

class EmployeeResponseMapperTest {

    private val id = 1
    private val name = "John"
    private val lastName = "Smith"
    private val description = "Something here"
    private val image = "image.com"
    private val rate = 4.5

    private val mapper = EmployeeMapperResponse()

    @Test
    fun transformSuccess() {
        //Given
        val entity = EmployeeResponse(
            id,
            name,
            lastName,
            image,
            description,
            rate
        )

        //When
        val result = mapper.transform(entity)

        Truth.assertThat(result).isNotNull()
        Truth.assertThat(result?.id).isEqualTo(id)
        Truth.assertThat(result?.firstName).isEqualTo(name)
        Truth.assertThat(result?.lastName).isEqualTo(lastName)
        Truth.assertThat(result?.image).isEqualTo(image)
        Truth.assertThat(result?.rating).isEqualTo(rate)
    }

    @Test
    fun transformSuccessMissingLastName() {
        //Given
        val entity = EmployeeResponse(
            id,
            name,
            null,
            image,
            description,
            rate
        )

        //When
        val result = mapper.transform(entity)

        Truth.assertThat(result).isNotNull()
        Truth.assertThat(result?.lastName).isEqualTo("-")
    }

    @Test
    fun transformSuccessMissingImage() {
        //Given
        val entity = EmployeeResponse(
            id,
            name,
            lastName,
            "",
            description,
            rate
        )

        //When
        val result = mapper.transform(entity)

        Truth.assertThat(result).isNotNull()
        Truth.assertThat(result?.image).isEqualTo("")
    }

    @Test
    fun transformSuccessMissingDescription() {
        //Given
        val entity = EmployeeResponse(
            id,
            name,
            lastName,
            image,
            null,
            rate
        )

        //When
        val result = mapper.transform(entity)

        Truth.assertThat(result).isNotNull()
        Truth.assertThat(result?.description).isEqualTo("-")
    }

    @Test
    fun transformCorruptedMissingId() {
        //Given
        val entity = EmployeeResponse(
            null,
            name,
            lastName,
            image,
            description,
            rate
        )

        //When
        val result = mapper.transform(entity)

        Truth.assertThat(result).isNull()
    }

    @Test
    fun transformCorruptedMissingName() {
        //Given
        val entity = EmployeeResponse(
            id,
            "",
            lastName,
            image,
            description,
            rate
        )

        //When
        val result = mapper.transform(entity)

        Truth.assertThat(result).isNull()
    }

    @Test
    fun transformCorruptedMissingRate() {
        //Given
        val entity = EmployeeResponse(
            id,
            name,
            lastName,
            image,
            description,
            null
        )

        //When
        val result = mapper.transform(entity)

        Truth.assertThat(result).isNull()
    }

    @Test
    fun transformCorruptedData() {
        //Given
        val entity: EmployeeResponse? = null

        //When
        val result = mapper.transform(entity)

        Truth.assertThat(result).isNull()
    }
}
