package com.example.cse_carapp.domain

class SortCarListUseCase(private val carListRepository: CarListRepository) {
    operator fun invoke() = carListRepository.sortCarList()
}