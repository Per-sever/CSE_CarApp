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

    override suspend fun getCarItem(carItemId: Int): CarItem {
        val dbModel = carItemListDao.getCarItem(carItemId)
        return mapper.mapDbModelToEntity(dbModel)
    }

    override fun showPhoto(carItemId: Int): CarItem {
        TODO("Not yet implemented")
    }

    override suspend fun addCarItem(carItem: CarItem) {
        val dbModel = mapper.mapEntityToDbModel(carItem)
        carItemListDao.addCarItem(dbModel)
    }

    override suspend fun editCarItem(carItem: CarItem) {
        val dbModel = mapper.mapEntityToDbModel(carItem)
        carItemListDao.addCarItem(dbModel)
    }

    override fun filterCarList(carCountry: String): LiveData<List<CarItem>> {
        return carItemListDao.filterCarList(carCountry).map {
            mapper.mapListModelToListEntity(it)
        }
    }

    override fun sortCarList(): LiveData<List<CarItem>> {
        return carItemListDao.sortCarListByAscName().map {
            mapper.mapListModelToListEntity(it)
        }
    }
}