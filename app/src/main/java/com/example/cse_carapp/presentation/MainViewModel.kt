package com.example.cse_carapp.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.cse_carapp.data.CarListRepositoryImpl
import com.example.cse_carapp.domain.AddCarItemUseCase
import com.example.cse_carapp.domain.GetCarListUseCase

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = CarListRepositoryImpl(application)

    private val addCarItemUseCase = AddCarItemUseCase(repository)

    val carList = GetCarListUseCase(repository).invoke()
}