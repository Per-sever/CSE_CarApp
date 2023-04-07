package com.example.cse_carapp.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.cse_carapp.data.CarListRepositoryImpl
import com.example.cse_carapp.domain.FilterCarListUseCase
import com.example.cse_carapp.domain.GetCarListUseCase
import com.example.cse_carapp.domain.SortCarListUseCase

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = CarListRepositoryImpl(application)

    val sortCarList = SortCarListUseCase(repository).invoke()

    val carList = GetCarListUseCase(repository).invoke()

    fun filterCarList(carCountry: String) = FilterCarListUseCase(repository).invoke(carCountry)

}