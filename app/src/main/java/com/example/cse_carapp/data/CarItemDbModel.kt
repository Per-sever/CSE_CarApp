package com.example.cse_carapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "car_items")
data class CarItemDbModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    val name: String,
    val price: Int,
    val country: String,
    val uriImageItem: String
)