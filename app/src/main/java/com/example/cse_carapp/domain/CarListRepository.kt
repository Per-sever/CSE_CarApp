package com.example.cse_carapp.domain

import androidx.lifecycle.LiveData

interface CarListRepository {
    //TODO add LiveDates to Lists
    fun getCarList(): LiveData<List<CarItem>>

    fun getCarItem(carItemId: Int): CarItem

    fun showPhoto(carItemId: Int): CarItem

    fun addCarItem(carItem: CarItem)

    fun editCarItem(carItem: CarItem)

    fun filterCarList(): List<CarItem>

    fun sortCarList(): List<CarItem>
}