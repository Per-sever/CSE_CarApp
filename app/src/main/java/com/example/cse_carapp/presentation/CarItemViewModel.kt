package com.example.cse_carapp.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.cse_carapp.data.CarListRepositoryImpl
import com.example.cse_carapp.domain.AddCarItemUseCase
import com.example.cse_carapp.domain.CarItem
import com.example.cse_carapp.domain.EditCarItemUseCase
import kotlinx.coroutines.launch

class CarItemViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = CarListRepositoryImpl(application)

    private val addCarItemUseCase = AddCarItemUseCase(repository)
    private val editCarItemUseCase = EditCarItemUseCase(repository)

    fun addCarItem(
        inputName: String?, inputPrice: String?, inputCountry: String?, inputImage:
        String?
    ) {
        val name = parseName(inputName)
        val price = parsePrice(inputPrice)
        val country = parseCountry(inputCountry)
        val imageCar = parseImage(inputImage)

        val carItem = CarItem(name, price, country, imageCar)
        viewModelScope.launch {
            addCarItemUseCase.invoke(carItem)
        }
    }

    private fun parseName(inputName: String?) = inputName?.trim() ?: ""

    private fun parsePrice(inputPrice: String?) = inputPrice?.toInt() ?: 0

    private fun parseCountry(inputCountry: String?) = inputCountry?.trim() ?: ""

    private fun parseImage(inputImage: String?) = inputImage?.trim() ?: ""
}