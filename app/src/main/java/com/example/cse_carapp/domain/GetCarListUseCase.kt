package com.example.cse_carapp.domain

class GetCarListUseCase(private val carListRepository: CarListRepository) {
    operator fun invoke() = carListRepository.getCarList()
}