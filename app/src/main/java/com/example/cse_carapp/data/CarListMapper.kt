package com.example.cse_carapp.data

import com.example.cse_carapp.domain.CarItem

class CarListMapper {
    fun mapDbModelToEntity(carItemDbModel: CarItemDbModel) = CarItem(
        id = carItemDbModel.id,
        name = carItemDbModel.name, country = carItemDbModel.country, price = carItemDbModel
            .price, uriImageItem = carItemDbModel.uriImageItem
    )

    fun mapListModelToListEntity(list: List<CarItemDbModel>) = list.map {
        mapDbModelToEntity(it)
    }

    fun mapEntityToDbModel(carItem: CarItem) = CarItemDbModel(
        id = carItem.id,
        name = carItem.name, country = carItem.country, price = carItem
            .price, uriImageItem = carItem.uriImageItem
    )
}