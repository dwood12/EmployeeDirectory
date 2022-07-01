package com.square.employeedirectory.network

import com.square.employeedirectory.data.DomainMapper
import com.square.employeedirectory.data.model.Employee

class EmployeeDTOMapper : DomainMapper<EmployeeDTO, Employee> {
    override fun toDomainModel(dto: EmployeeDTO): Employee {
        return Employee(
            dto.uuid,
            dto.fullName,
            dto.phoneNumber,
            dto.photoUrlSmall,
            dto.team
        )
    }
}