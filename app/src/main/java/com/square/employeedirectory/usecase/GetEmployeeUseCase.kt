package com.square.employeedirectory.usecase

import com.square.employeedirectory.data.model.Employee
import com.square.employeedirectory.data.EmployeeRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

enum class EmployeeDirectoryType {
    EMPLOYEE,
    MALFORMED,
    EMPTY
}

class GetEmployeeUseCase(
    private val employeeRepository: EmployeeRepository,
    dispatcher: CoroutineDispatcher = Dispatchers.Default
) : UseCase<EmployeeDirectoryType, List<Employee>>(dispatcher) {
    override suspend fun execute(parameters: EmployeeDirectoryType): List<Employee> {
        return when (parameters) {
            EmployeeDirectoryType.EMPLOYEE -> employeeRepository.getEmployees()
            EmployeeDirectoryType.MALFORMED -> employeeRepository.getMalformedEmployees()
            EmployeeDirectoryType.EMPTY -> employeeRepository.getEmptyEmployees()
        }
    }
}