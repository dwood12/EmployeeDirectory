package com.square.employeedirectory.network

import com.square.employeedirectory.data.DomainMapper
import com.square.employeedirectory.data.model.Employee

class EmployeeDTOMapper : DomainMapper<EmployeeDTO, Employee> {
    override fun toDomainModel(dto: EmployeeDTO): Employee {
        return Employee(
            dto.uuid,
            dto.fullName,
            dto.phoneNumber,
            dto.emailAddress,
            dto.photoUrlSmall,
            dto.photoUrlLarge,
            dto.team,
            dto.employeeType
        )
    }

    override fun toDto(domainModel: Employee): EmployeeDTO {
        return EmployeeDTO(
            domainModel.uuid,
            domainModel.fullName,
            domainModel.phoneNumber,
            domainModel.emailAddress,
            domainModel.photoUrlSmall,
            domainModel.photoUrlLarge,
            domainModel.team,
            domainModel.employeeType
        )
    }

}