package com.square.employeedirectory.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "employee")
data class Employee(
    @PrimaryKey val uuid: String,
    val fullName: String,
    val phoneNumber: String,
    val emailAddress: String,
    val photoUrlSmall: String,
    val photoUrlLarge: String,
    val team: String,
    val employeeType: String
)
