package com.example.cse_carapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CarItemListDao {
    @Query("SELECT * FROM car_items")
    fun getCarList(): LiveData<List<CarItemDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addCarItem(carItemDbModel: CarItemDbModel)

    @Query("SELECT * FROM car_items WHERE id=:carItemId LIMIT 1")
    fun getCarItem(carItemId: Int): CarItemDbModel

    @Query("SELECT * FROM car_items WHERE country=:countryName")
    fun filterCarList(countryName: String): LiveData<List<CarItemDbModel>>

    @Query("SELECT * FROM car_items ORDER BY name ASC")
    fun sortCarListByAscName(): LiveData<List<CarItemDbModel>>

    @Query("SELECT * FROM car_items ORDER BY name DESC")
    fun sortCarListByDescName(): LiveData<List<CarItemDbModel>>

}