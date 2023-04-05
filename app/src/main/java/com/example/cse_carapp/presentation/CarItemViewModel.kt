package com.example.cse_carapp.presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.cse_carapp.data.CarListRepositoryImpl
import com.example.cse_carapp.domain.AddCarItemUseCase
import com.example.cse_carapp.domain.CarItem
import com.example.cse_carapp.domain.EditCarItemUseCase
import com.example.cse_carapp.domain.GetCarItemUseCase
import kotlinx.coroutines.launch

class CarItemViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = CarListRepositoryImpl(application)

    private val addCarItemUseCase = AddCarItemUseCase(repository)
    private val editCarItemUseCase = EditCarItemUseCase(repository)
    private val getCarItemUseCase = GetCarItemUseCase(repository)

    private val _carItemById = MutableLiveData<CarItem>()
    val carItemById: LiveData<CarItem>
        get() = _carItemById

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

    fun editCarItem(
        inputName: String?, inputPrice: String?, inputCountry: String?, inputImage:
        String?
    ) {
        val name = parseName(inputName)
        val price = parsePrice(inputPrice)
        val country = parseCountry(inputCountry)
        val imageCar = parseImage(inputImage)

        viewModelScope.launch {
            _carItemById.value?.let {
                val item =
                    it.copy(name = name, price = price, country = country, uriImageItem = imageCar)
                editCarItemUseCase(item)
            }
        }
    }

    fun getCarItemUseCase(carItemId: Int) {
        viewModelScope.launch {
            val item = getCarItemUseCase.invoke(carItemId)
            _carItemById.value = item
        }
    }


    private fun parseName(inputName: String?) = inputName?.trim() ?: ""

    private fun parsePrice(inputPrice: String?) = inputPrice?.toInt() ?: 0

    private fun parseCountry(inputCountry: String?) = inputCountry?.trim() ?: ""

    private fun parseImage(inputImage: String?) = inputImage?.trim() ?: ""
}