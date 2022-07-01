package com.square.employeedirectory.ui

import com.square.employeedirectory.data.model.Employee

data class EmployeeUiData(
    val employees: List<Employee> = emptyList(),
    val isLoading: Boolean = false,
    val error: Boolean = false
)