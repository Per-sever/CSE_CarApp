package com.example.cse_carapp.data

import android.app.Application
import com.example.cse_carapp.domain.CarItem
import com.example.cse_carapp.domain.CarListRepository

class CarListRepositoryImpl(application: Application) : CarListRepository {

    private val carItemListDao = AppDatabase.getInstance(application).CarItemListDao()


    override fun getCarList(): List<CarItem> {
        TODO("Not yet implemented")
    }

    override fun showPhoto(idCarItem: Int): CarItem {
        TODO("Not yet implemented")
    }

    override fun addCarItem(carItem: CarItem) {
        TODO("Not yet implemented")
    }

    override fun editCarItem(carItem: CarItem) {
        TODO("Not yet implemented")
    }

    override fun filterCarList(): List<CarItem> {
        TODO("Not yet implemented")
    }

    override fun sortCarList(): List<CarItem> {
        TODO("Not yet implemented")
    }
}