package com.example.cse_carapp.domain

class GetCarItemUseCase(private val getCarItemUseCase: CarListRepository) {
    operator fun invoke(carItemId: Int) = getCarItemUseCase.getCarItem(carItemId)
}