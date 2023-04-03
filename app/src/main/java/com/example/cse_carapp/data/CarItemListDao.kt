package com.example.cse_carapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cse_carapp.data.CarItemDbModel

@Dao
interface CarItemListDao {
    //TODO Add LiveDates in Lists

    @Query("SELECT * FROM car_items")
    fun getCarList(): LiveData<List<CarItemDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addCarItem(carItemDbModel: CarItemDbModel)

    @Query("SELECT * FROM car_items WHERE id=:carItemId LIMIT 1")
    fun getCarItem(carItemId: Int): CarItemDbModel

    @Query("SELECT * FROM car_items WHERE name = :name")
    fun filterCarList(name: String): List<CarItemDbModel>

    @Query("SELECT * FROM car_items ORDER BY name ASC")
    fun sortCarListByAscName(): List<CarItemDbModel>

    @Query("SELECT * FROM car_items ORDER BY name DESC")
    fun sortCarListByDescName(): List<CarItemDbModel>

}