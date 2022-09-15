package com.bejussi.shopply.presentation.sheet_dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.bejussi.shopply.R
import com.bejussi.shopply.databinding.FragmentAddNewListSheetBinding
import com.bejussi.shopply.domain.model.Category
import com.bejussi.shopply.presentation.view_model.CategoryViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddNewListSheet : BottomSheetDialogFragment() {

    private var _binding: FragmentAddNewListSheetBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CategoryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddNewListSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addButton.setOnClickListener {
            val nameCategory = binding.nameEditText.text.toString()

            if (nameCategory.isEmpty()) {
                showErrorToast()
            } else {
                val category = createCategory(nameCategory)
                addCategory(category)
            }
        }
    }

    private fun createCategory(nameCategory: String): Category {
        return Category(
            name = nameCategory
        )
    }

    private fun addCategory(category: Category) {
        viewModel.insertCategory(category)
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