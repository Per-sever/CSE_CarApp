package com.example.cse_carapp.domain

import androidx.lifecycle.LiveData

interface CarListRepository {

    fun getCarList(): LiveData<List<CarItem>>

    suspend fun getCarItem(carItemId: Int): CarItem

    fun showPhoto(carItemId: Int): CarItem

    suspend fun addCarItem(carItem: CarItem)

    suspend fun editCarItem(carItem: CarItem)

    fun filterCarList(carCountry: String): LiveData<List<CarItem>>

    fun sortCarList(): LiveData<List<CarItem>>
}