package com.example.cse_carapp.domain

class EditCarItemUseCase(private val carListRepository: CarListRepository) {
    operator fun invoke(carItem: CarItem) = carListRepository.editCarItem(carItem)
}