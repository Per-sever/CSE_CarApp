package com.example.cse_carapp.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.cse_carapp.databinding.FragmentCarItemBinding
import com.example.cse_carapp.domain.CarItem

class CarItemFragment : Fragment() {

    private lateinit var viewModel: CarItemViewModel

    private var _binding: FragmentCarItemBinding? = null
    private val binding: FragmentCarItemBinding
        get() = _binding ?: throw RuntimeException("FragmentCarItemBinding == null")


    private var screenMode = MODE_UNKNOWN
    private var carItemId = CarItem.UNDEFINED_ID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseParams()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCarItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[CarItemViewModel::class.java]
        launchMode()

    }

    private fun launchMode() {
        Log.d("CarItemFragment", "LaunchMode")
        when (screenMode) {
            MODE_ADD -> launchAddMode()
            MODE_EDIT -> launchEditMode()
        }
    }

    private fun launchAddMode() {
        Log.d("CarItemFragment", "AddMode")
        binding.btnAdd.setOnClickListener {
            viewModel.addCarItem(
                binding.carNameEt.text?.toString(),
                binding.carPriceEt.text?.toString(),
                binding.countryNameEt.text?.toString(),
                "123"
            )
        }
    }

    private fun launchEditMode() {
        Log.d("CarItemFragment", "EditMode")
        viewModel.getCarItemUseCase(carItemId)
        viewModel.carItemById.observe(viewLifecycleOwner) {
            binding.carNameEt.setText(it.name)
            binding.countryNameEt.setText(it.country)
            binding.carPriceEt.setText(it.price.toString())
        }
        binding.btnAdd.setOnClickListener {
            viewModel.editCarItem(
                binding.carNameEt.text?.toString(),
                binding.carPriceEt.text?.toString(),
                binding.countryNameEt.text?.toString(),
                "123"
            )
        }
    }

    private fun parseParams() {
        val args = requireArguments()
        if (!args.containsKey(SCREEN_MODE)) {
            throw RuntimeException("Param screen is absent")
        }

        val mode = args.getString(SCREEN_MODE)
        if (mode != MODE_EDIT && mode != MODE_ADD) {
            throw RuntimeException("Unknown screen mode - $mode")
        }
        screenMode = mode

        if (screenMode == MODE_EDIT) {
            if (!args.containsKey(CAR_ITEM_ID)) {
                throw RuntimeException("Param car item id is absent")
            }
            carItemId = args.getInt(CAR_ITEM_ID, CarItem.UNDEFINED_ID)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val SCREEN_MODE = "extra_mode"
        private const val CAR_ITEM_ID = "car_item_id"
        private const val MODE_ADD = "mode_add"
        private const val MODE_EDIT = "mode_edit"
        private const val MODE_UNKNOWN = ""

        fun newInstanceAddItem(): CarItemFragment {
            return CarItemFragment().apply {
                arguments = Bundle().apply {
                    putString(SCREEN_MODE, MODE_ADD)
                }
            }
        }

        fun newInstanceEditItem(carItemId: Int): CarItemFragment {
            return CarItemFragment().apply {
                arguments = Bundle().apply {
                    putString(SCREEN_MODE, MODE_EDIT)
                    putInt(CAR_ITEM_ID, carItemId)
                }
            }
        }
    }
}