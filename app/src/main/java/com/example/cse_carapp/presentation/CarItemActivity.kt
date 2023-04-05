package com.example.cse_carapp.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cse_carapp.R
import com.example.cse_carapp.databinding.ActivityCarItemBinding
import com.example.cse_carapp.domain.CarItem

class CarItemActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCarItemBinding

    private var screenMode = MODE_UNKNOWN
    private var carItemId = CarItem.UNDEFINED_ID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCarItemBinding.inflate(layoutInflater)
        setContentView(binding.root)
        parseIntent()
        if (savedInstanceState == null) {
            launchMode()
        }

    }

    private fun launchMode() {
        val fragment = when (screenMode) {
            MODE_ADD -> CarItemFragment.newInstanceAddItem()
            MODE_EDIT -> CarItemFragment.newInstanceEditItem(carItemId)
            else -> throw RuntimeException("Unknown screen mode $screenMode")
        }
        supportFragmentManager.beginTransaction()
            .replace(R.id.car_item_container, fragment)
            .commit()
    }

    private fun parseIntent() {
        if (!intent.hasExtra(EXTRA_SCREEN_MODE)) {
            throw RuntimeException("Param screen is absent")
        }

        val mode = intent.getStringExtra(EXTRA_SCREEN_MODE)
        if (mode != MODE_EDIT && mode != MODE_ADD) {
            throw RuntimeException("Unknown screen mode - $mode")
        }
        screenMode = mode

        if (screenMode == MODE_EDIT) {
            if (!intent.hasExtra(EXTRA_CAR_ITEM_ID)) {
                throw RuntimeException("Param car item id is absent")
            }
            carItemId = intent.getIntExtra(EXTRA_CAR_ITEM_ID, CarItem.UNDEFINED_ID)
        }
    }

    companion object {
        private const val EXTRA_SCREEN_MODE = "extra_mode"
        private const val EXTRA_CAR_ITEM_ID = "extra_car_item_id"
        private const val MODE_ADD = "mode_add"
        private const val MODE_EDIT = "mode_edit"
        private const val MODE_UNKNOWN = ""


        fun newIntentAddItem(context: Context): Intent {
            val intent = Intent(context, CarItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_ADD)
            return intent
        }

        fun newIntentEditItem(context: Context, carItemId: Int): Intent {
            val intent = Intent(context, CarItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_EDIT)
            intent.putExtra(EXTRA_CAR_ITEM_ID, carItemId)
            return intent
        }
    }
}