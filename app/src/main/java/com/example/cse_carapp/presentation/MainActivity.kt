package com.example.cse_carapp.presentation

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
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
        requestPermission()
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.carList.observe(this) {
            adapterCarList.submitList(it)
        }
        val buttonAdd = binding.buttonAdd
        val buttonSort = binding.buttonSort
        val buttonFilter = binding.buttonFilter
        buttonAdd.setOnClickListener {
            val intent = CarItemActivity.newIntentAddItem(this)
            startActivity(intent)
        }
        buttonSort.setOnClickListener {
            viewModel.sortCarList.observe(this) {
                setupRecyclerView()
                adapterCarList.submitList(it)
            }
        }
        buttonFilter.setOnClickListener {
            val filterEt = binding.countryFilterEt
            viewModel.filterCarList(filterEt.text.toString()).observe(this) {
                setupRecyclerView()
                adapterCarList.submitList(it)
            }
        }
    }


    override fun onResume() {
        super.onResume()
        setupRecyclerView()
    }

    private fun requestPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_IMAGES)
            != PackageManager.PERMISSION_GRANTED
        ) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.READ_MEDIA_IMAGES),
                    1
                )
            }
        }
    }

    private fun setupRecyclerView() {
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
