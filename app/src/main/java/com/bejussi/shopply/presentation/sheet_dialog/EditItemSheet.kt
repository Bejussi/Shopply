package com.bejussi.shopply.presentation.sheet_dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bejussi.shopply.R
import com.bejussi.shopply.databinding.FragmentEditItemSheetBinding
import com.bejussi.shopply.domain.model.Item
import com.bejussi.shopply.presentation.view_model.ItemViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditItemSheet : BottomSheetDialogFragment() {

    private var _binding: FragmentEditItemSheetBinding? = null
    private val binding get() = _binding!!

    val args: EditItemSheetArgs by navArgs()

    private val viewModel: ItemViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditItemSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            nameEditText.setText(args.item.name)
            countEditText.setText(args.item.count.toString())
            priceEditText.setText(args.item.price.toString())
        }

        binding.editButton.setOnClickListener {
            val nameItem = binding.nameEditText.text.toString()
            val countItem = binding.countEditText.text.toString()
            val priceItem = binding.priceEditText.text.toString()

            if (nameItem.isEmpty() || countItem.isEmpty() || priceItem.isEmpty()) {
                showErrorToast()
            } else {
                val item = createItem(nameItem, countItem, priceItem)
                editItem(item)
            }
        }
    }

    private fun createItem(nameItem: String, countItem: String, priceItem: String): Item {
        return Item(
            id = args.item.id,
            name = nameItem,
            count = countItem.toFloat(),
            bought = false,
            categoryId = args.item.categoryId,
            price = priceItem.toFloat(),
            isExpandable = false
        )
    }

    private fun editItem(item: Item) {
        viewModel.editItem(item)
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