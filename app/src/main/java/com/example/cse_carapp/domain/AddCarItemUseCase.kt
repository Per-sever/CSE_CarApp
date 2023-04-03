package com.example.cse_carapp.domain

class AddCarItemUseCase(private val carListRepository: CarListRepository) {
    // TODO add coroutines in the future
    operator fun invoke(carItem: CarItem) = carListRepository.addCarItem(carItem)
}