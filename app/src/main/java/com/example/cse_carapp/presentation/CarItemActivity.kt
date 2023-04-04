package com.example.cse_carapp.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.cse_carapp.R
import com.example.cse_carapp.databinding.ActivityCarItemBinding

class CarItemActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCarItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("Hello", "Created!")
        super.onCreate(savedInstanceState)
        val binding = ActivityCarItemBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            val fragment = CarItemFragment.newInstanceAddItem()
            supportFragmentManager.beginTransaction().replace(R.id.car_item_container, fragment)
                .commit()
        }

    }

    //    private fun checkIntent() {
//        if (!intent.hasExtra(EXTRA_SCREEN_MODE)) {
//            throw RuntimeException("Param screen is absent")
//        }
//    }
//
    companion object {
        private const val EXTRA_SCREEN_MODE = "extra_mode"
        private const val MODE_ADD = "mode_add"

        fun newIntentAddItem(context: Context): Intent {
            val intent = Intent(context, CarItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_ADD)
            return intent
        }
    }
}