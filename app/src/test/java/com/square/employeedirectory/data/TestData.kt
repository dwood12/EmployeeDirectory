package com.square.employeedirectory.data

import com.square.employeedirectory.data.model.Employee
import com.square.employeedirectory.network.EmployeeDTO
import com.square.employeedirectory.network.EmployeesDTO

val testEmployeeDto = EmployeeDTO(
    "1234",
    "Jack Dorsey",
    "5555555555",
    "jdorsey@squareup.com",
    "https://smallphoto.fake",
    "https://largephoto.fake",
    "team1",
    "FULL_TIME"
)

val testEmployeesDTO = EmployeesDTO(listOf(testEmployeeDto))

val testEmployee = Employee(
    "1234",
    "Jack Dorsey",
    "5555555555",
    "https://smallphoto.fake",
    "team1"
)