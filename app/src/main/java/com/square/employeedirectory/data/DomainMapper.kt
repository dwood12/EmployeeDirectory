package com.square.employeedirectory.data

interface DomainMapper<DTO, DM> {
    fun toDomainModel(dto: DTO): DM
}