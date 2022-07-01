package com.square.employeedirectory.network

import retrofit2.http.GET

interface EmployeeApi {
    @GET("employees.json")
    suspend fun getEmployees(): EmployeesDTO

    @GET("employees_empty.json")
    suspend fun getEmptyEmployees(): EmployeesDTO

    @GET("employees_malformed.json")
    suspend fun getMalformedEmployees(): EmployeesDTO
}