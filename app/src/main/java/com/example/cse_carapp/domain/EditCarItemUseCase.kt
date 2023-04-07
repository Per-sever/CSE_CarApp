package com.example.cse_carapp.domain

class EditCarItemUseCase(private val carListRepository: CarListRepository) {
    suspend operator fun invoke(carItem: CarItem) = carListRepository.editCarItem(carItem)
}