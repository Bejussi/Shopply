package com.bejussi.shopply.presentation.sheet_dialog

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.datastore.preferences.protobuf.FloatValue
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bejussi.shopply.R
import com.bejussi.shopply.databinding.FragmentAddNewItemSheetBinding
import com.bejussi.shopply.domain.model.Item
import com.bejussi.shopply.presentation.view_model.ItemViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import java.text.NumberFormat

@AndroidEntryPoint
class AddNewItemSheet() : BottomSheetDialogFragment() {

    private var _binding: FragmentAddNewItemSheetBinding? = null
    private val binding get() = _binding!!

    val args: AddNewItemSheetArgs by navArgs()

    private val viewModel: ItemViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddNewItemSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addButton.setOnClickListener {
            val nameItem = binding.nameEditText.text.toString()
            val countItem = binding.countEditText.text.toString()
            val priceItem = binding.priceEditText.text.toString()

            if (nameItem.isEmpty() || countItem.isEmpty()) {
                showErrorToast()
            } else {
                val item = createItem(nameItem, countItem, priceItem)
                addItem(item)
            }
        }
    }

    private fun createItem(nameItem: String, countItem: String, priceItem: String): Item {
        return Item(
            id = 0,
            name = nameItem,
            count = countItem.toFloat(),
            bought = false,
            categoryId = args.category.id,
            price = priceItem.toFloat(),
            isExpandable = false
        )
    }

    private fun addItem(item: Item) {
        viewModel.insertItem(item)
        dismiss()
    }

    private fun showErrorToast() {
        Toast.makeText(context, R.string.dialog_toast, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}