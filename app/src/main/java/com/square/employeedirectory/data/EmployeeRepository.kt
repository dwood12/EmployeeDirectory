package com.square.employeedirectory.data

import com.square.employeedirectory.data.model.Employee
import com.square.employeedirectory.network.EmployeeApi
import com.square.employeedirectory.network.EmployeeDTOMapper
import com.square.employeedirectory.network.EmployeesDTO

class EmployeeRepository(private val employeeDAO: EmployeeDAO, private val employeeApi: EmployeeApi)  {
    suspend fun getEmployees(): List<Employee> {
        val res = employeeApi.getEmployees()

        return processResponse(res)
    }

    suspend fun getMalformedEmployees(): List<Employee> {
        val res = employeeApi.getMalformedEmployees()

        return processResponse(res)
    }

    suspend fun getEmptyEmployees(): List<Employee> {
        val res = employeeApi.getEmptyEmployees()

        return processResponse(res)
    }

    private fun processResponse(employeesDTO: EmployeesDTO): List<Employee> {
        val mapper = EmployeeDTOMapper()
        employeeDAO.insertAll(employeesDTO.employees.map(mapper::toDomainModel))

        return employeeDAO.getAll()
    }
}