package com.example.cse_carapp.domain

class EditCatItemUseCase(private val carListRepository: CarListRepository) {
    operator fun invoke(carItem: CarItem) = carListRepository.editCarItem(carItem)
}