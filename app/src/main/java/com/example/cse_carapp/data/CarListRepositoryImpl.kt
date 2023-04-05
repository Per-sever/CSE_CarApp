package com.example.cse_carapp.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.cse_carapp.domain.CarItem
import com.example.cse_carapp.domain.CarListRepository

class CarListRepositoryImpl(application: Application) : CarListRepository {

    private val carItemListDao = AppDatabase.getInstance(application).CarItemListDao()
    private val mapper = CarListMapper()

    override fun getCarList(): LiveData<List<CarItem>> {
        return carItemListDao.getCarList().map {
            mapper.mapListModelToListEntity(it)
        }
    }

    override fun getCarItem(carItemId: Int): CarItem {
        val dbModel = carItemListDao.getCarItem(carItemId)
        return mapper.mapDbModelToEntity(dbModel)
    }

    override fun showPhoto(carItemId: Int): CarItem {
        TODO("Not yet implemented")
    }

    override fun addCarItem(carItem: CarItem) {
        val dbModel = mapper.mapEntityToDbModel(carItem)
        carItemListDao.addCarItem(dbModel)
    }

    override fun editCarItem(carItem: CarItem) {
        val dbModel = mapper.mapEntityToDbModel(carItem)
        carItemListDao.addCarItem(dbModel)
    }

    override fun filterCarList(): List<CarItem> {
        TODO("Not yet implemented")
    }

    override fun sortCarList(): List<CarItem> {
        TODO("Not yet implemented")
    }
}