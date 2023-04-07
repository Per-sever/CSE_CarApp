package com.example.cse_carapp.domain

class AddCarItemUseCase(private val carListRepository: CarListRepository) {
    suspend operator fun invoke(carItem: CarItem) = carListRepository.addCarItem(carItem)
}