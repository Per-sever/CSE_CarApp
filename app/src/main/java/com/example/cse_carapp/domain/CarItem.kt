package com.example.cse_carapp.domain

data class CarItem(
    val name: String,
    val price: Int,
    val country: String,
    val uriImageItem: String,
    var id: Int = UNDEFINED_ID
) {
    companion object {
        const val UNDEFINED_ID = 0
    }
}