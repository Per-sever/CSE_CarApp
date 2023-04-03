package com.example.cse_carapp

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import com.bumptech.glide.Glide
import com.example.cse_carapp.data.AppDatabase
import com.example.cse_carapp.data.CarItemDbModel

class MainActivity : AppCompatActivity() {
//    private val listOfCars = mutableListOf<CarItemDbModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val carItemListDao = AppDatabase.getInstance(application).CarItemListDao()

//        for (i in 0..10) {
//            carItemListDao.addCarItem(CarItemDbModel(0, "$i", i + 100, "Russia", "URLSTRING"))
//        }
        Log.d("SHOW_DB","${carItemListDao.filterCarList("Andrey")}")
        findViewById<Button>(R.id.button).setOnClickListener {
            Glide.with(this)
                .load("content://com.google.android.apps.photos.contentprovider/-1/1/content%3A%2F%2Fmedia%2Fexternal%2Fimages%2Fmedia%2F1000000033/ORIGINAL/NONE/image%2Fjpeg/2085097503")
                .into(findViewById(R.id.iv))
//            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
//            registerForActivity.launch(intent)
        }
    }

    var registerForActivity = registerForActivityResult(
        ActivityResultContracts
            .StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val imageUri = result.data?.data
            Log.d("Hello", imageUri.toString())
            Glide.with(this)
                .load(imageUri.toString())
                .into(findViewById(R.id.iv))
            Log.d("Hello", "Loaded")
        }
    }
}