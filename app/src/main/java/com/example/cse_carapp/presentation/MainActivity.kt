package com.example.cse_carapp.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.cse_carapp.databinding.ActivityMainBinding
import com.example.cse_carapp.presentation.adapters.CarListAdapter

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var viewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val adapter = CarListAdapter()
        binding.rvCarList.adapter = adapter
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.carList.observe(this) {
            adapter.submitList(it)
        }
        val buttonAdd = binding.buttonAdd
        buttonAdd.setOnClickListener {
            Log.d("CarItemActivity", "Click!")
            val intent = CarItemActivity.newIntentAddItem(this)
            startActivity(intent)
//            val fragment = CarItemFragment.newInstanceAddItem()
////            supportFragmentManager.popBackStack()
////            supportFragmentManager.beginTransaction().replace(R.id.car_item_container, fragment)
////                .addToBackStack(null).commit()
        }
//        val carItemListDao = AppDatabase.getInstance(application).CarItemListDao()
//        Log.d("SHOW_DB","${carItemListDao.filterCarList("Andrey")}")

    }

}


//    findViewById<Button>(R.id.button).setOnClickListener {
//        Glide.with(this)
//            .load("content://com.google.android.apps.photos.contentprovider/-1/1/content%3A%2F%2Fmedia%2Fexternal%2Fimages%2Fmedia%2F1000000033/ORIGINAL/NONE/image%2Fjpeg/2085097503")
//            .into(findViewById(R.id.iv))
//
//    }
//            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
//            registerForActivity.launch(intent)
//    var registerForActivity = registerForActivityResult(
//        ActivityResultContracts
//            .StartActivityForResult()
//    ) { result ->
//        if (result.resultCode == Activity.RESULT_OK) {
//            val imageUri = result.data?.data
//            Log.d("Hello", imageUri.toString())
//            Glide.with(this)
//                .load(imageUri.toString())
//                .into(findViewById(R.id.iv))
//            Log.d("Hello", "Loaded")
//        }
//    }