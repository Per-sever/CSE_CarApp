package com.example.cse_carapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.cse_carapp.databinding.ActivityMainBinding
import com.example.cse_carapp.presentation.adapters.CarListAdapter

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var viewModel: MainViewModel
    private val adapterCarList by lazy {
        CarListAdapter()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.carList.observe(this) {
            adapterCarList.submitList(it)
        }
        val buttonAdd = binding.buttonAdd
        buttonAdd.setOnClickListener {
            val intent = CarItemActivity.newIntentAddItem(this)
            startActivity(intent)
        }
    }


    override fun onResume() {
        super.onResume()
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        //todo OPTIMIZE
        val rvCarList = binding.rvCarList
        rvCarList.adapter = adapterCarList
        setupSwipeListener(rvCarList)
    }

    private fun setupSwipeListener(rvCarList: RecyclerView?) {
        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = adapterCarList.currentList[viewHolder.adapterPosition]
                val intent = CarItemActivity.newIntentEditItem(this@MainActivity, item.id)
                startActivity(intent)
            }
        }
        ItemTouchHelper(callback).attachToRecyclerView(rvCarList)
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