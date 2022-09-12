package com.bejussi.shopply.presentation.sheet_dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bejussi.shopply.R
import com.bejussi.shopply.databinding.FragmentAddNewItemSheetBinding
import com.bejussi.shopply.domain.model.Item
import com.bejussi.shopply.presentation.view_model.ItemViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

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

            if (nameItem.isEmpty() || countItem.isEmpty()) {
                showErrorToast()
            } else {
                val item = createItem(nameItem, countItem)
                addItem(item)
            }
        }
    }

    private fun createItem(nameItem: String, countItem: String): Item {
        return Item(
            id = 0,
            name = nameItem,
            count = countItem.toInt(),
            bought = false,
            category = args.categoryName
        )
    }

    private fun addItem(item: Item) {
        viewModel.insertItem(item)
        dismiss()
    }

    private fun showErrorToast() {
        Toast.makeText(context, R.string.dialog_toast, Toast.LENGTH_SHORT).show()
    }

}