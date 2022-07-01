package com.square.employeedirectory.network

import com.google.gson.annotations.SerializedName

data class EmployeesDTO(
    val employees: List<EmployeeDTO>
)

data class EmployeeDTO(
    val uuid: String,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("phone_number")
    val phoneNumber: String,
    @SerializedName("email_address")
    val emailAddress: String,
    @SerializedName("photo_url_small")
    val photoUrlSmall: String,
    @SerializedName("photo_url_large")
    val photoUrlLarge: String,
    val team: String,
    @SerializedName("employee_type")
    val employeeType: String
)