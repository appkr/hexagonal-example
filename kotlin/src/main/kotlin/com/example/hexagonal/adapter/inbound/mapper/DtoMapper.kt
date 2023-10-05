package com.example.hexagonal.adapter.inbound.mapper

abstract class DtoMapper<E, D> {
    fun toDto(entityList: List<E>): List<D> {
        val dtoList = mutableListOf<D>()
        for (entity in entityList) {
            dtoList.add(toDto(entity))
        }
        return dtoList
    }

    abstract fun toDto(entity: E): D
}
