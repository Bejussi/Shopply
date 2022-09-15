package com.bejussi.shopply.presentation.sheet_dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bejussi.shopply.R
import com.bejussi.shopply.databinding.FragmentEditCategorySheetBinding
import com.bejussi.shopply.domain.model.Category
import com.bejussi.shopply.presentation.view_model.CategoryViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditCategorySheet : BottomSheetDialogFragment() {

    private var _binding: FragmentEditCategorySheetBinding? = null
    private val binding get() = _binding!!

    val args: EditCategorySheetArgs by navArgs()

    private val viewModel: CategoryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditCategorySheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.nameEditText.setText(args.category.name)

        binding.editButton.setOnClickListener {
            val nameCategory = binding.nameEditText.text.toString()

            if (nameCategory.isEmpty()) {
                showErrorToast()
            } else {
                val category = createCategory(nameCategory)
                updateCategory(category)
            }
        }
    }

    private fun createCategory(nameCategory: String): Category {
        return Category(
            name = nameCategory
        )
    }

    private fun updateCategory(category: Category) {
        viewModel.editCategory(category)
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