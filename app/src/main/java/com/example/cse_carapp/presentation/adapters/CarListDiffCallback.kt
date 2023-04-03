package com.example.cse_carapp.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.cse_carapp.domain.CarItem

class CarListDiffCallback : DiffUtil.ItemCallback<CarItem>() {
    override fun areItemsTheSame(oldItem: CarItem, newItem: CarItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CarItem, newItem: CarItem): Boolean {
        return oldItem == newItem
    }
}