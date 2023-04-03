package com.example.cse_carapp.presentation.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.cse_carapp.databinding.ItemCarBinding
import com.example.cse_carapp.domain.CarItem

class CarListAdapter : ListAdapter<CarItem, CarItemViewHolder>(CarListDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarItemViewHolder {
        val binding = ItemCarBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CarItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CarItemViewHolder, position: Int) {
        val binding = holder.binding
        val carItem = getItem(position)
        with(binding) {
            brandTv.text = carItem.name
            countryTv.text = carItem.country
            priceTv.text = carItem.price.toString()
        }
    }
}