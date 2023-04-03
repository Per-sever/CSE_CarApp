package com.example.cse_carapp.domain

import com.example.cse_carapp.domain.CarItem

interface CarListRepository {
    //TODO add LiveDates to Lists
    fun getCarList(): List<CarItem>

    fun showPhoto(idCarItem: Int): CarItem

    fun addCarItem(carItem: CarItem)

    fun editCarItem(carItem: CarItem)

    fun filterCarList(): List<CarItem>

    fun sortCarList(): List<CarItem>
}