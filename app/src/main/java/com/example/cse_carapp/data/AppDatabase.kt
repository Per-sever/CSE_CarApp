package com.example.cse_carapp.data

import android.app.Application
import androidx.room.Database
import androidx.room.Room

import androidx.room.RoomDatabase

@Database(entities = [CarItemDbModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun CarItemListDao(): CarItemListDao

    companion object {
        private var INSTANCE: AppDatabase? = null
        private val LOCK = Any()
        private const val DB_NAME = "car_item.db"

        fun getInstance(application: Application): AppDatabase {
            INSTANCE?.let {
                return it
            }
            synchronized(LOCK) {
                INSTANCE?.let {
                    return it
                }
                val db = Room.databaseBuilder(
                    application, AppDatabase::class.java, DB_NAME
                ).allowMainThreadQueries().build()
                INSTANCE = db
                return db
            }
        }
    }
}