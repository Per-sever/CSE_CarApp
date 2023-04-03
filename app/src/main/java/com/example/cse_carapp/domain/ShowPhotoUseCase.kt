package com.example.cse_carapp.domain

class ShowPhotoUseCase(private val carListRepository: CarListRepository) {
    operator fun invoke(carItemId: Int) = carListRepository.showPhoto(carItemId)
}