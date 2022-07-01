package com.square.employeedirectory.data.model

data class Employee(
    val uuid: String,
    val fullName: String,
    val phoneNumber: String,
    val photoUrl: String,
    val team: String
)