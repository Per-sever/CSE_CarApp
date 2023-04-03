package com.example.cse_carapp.domain

class FilterCarListUseCase(private val carListRepository: CarListRepository) {
    operator fun invoke() = carListRepository.filterCarList()
}