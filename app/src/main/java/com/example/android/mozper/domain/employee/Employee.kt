package com.example.android.mozper.domain.employee

data class Employee(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val image: String,
    val description: String,
    val rating: Double
) {

    val fullName: String get() = "$firstName $lastName"
    val ratingText: String get() = "$rating"
}
