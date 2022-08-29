package com.bejussi.shopply.presentation.dialog.edit_category_dialog

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import androidx.lifecycle.LiveData
import com.bejussi.shopply.R
import com.bejussi.shopply.databinding.DialogEditCategoryBinding
import com.bejussi.shopply.domain.model.Category
import kotlinx.coroutines.flow.Flow

class EditCategoryDialog(
    context: Context,
    private val editCategoryDialogListener: EditCategoryDialogListener,
    private val category: Category
) : AppCompatDialog(context) {

    private var _binding: DialogEditCategoryBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = DialogEditCategoryBinding.inflate(LayoutInflater.from(context))
        setContentView(binding.root)
        window
            ?.setLayout(
                ViewGroup.LayoutParams.FILL_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )

        binding.nameEditText.setText(category.name)
        binding.emojiEditText.setText(category.emoji)

        binding.editButton.setOnClickListener {
            val nameCategory = binding.nameEditText.text.toString()
            val emojiCategory = binding.emojiEditText.text.toString()

            if (nameCategory.isEmpty()||emojiCategory.isEmpty()) {
                Toast.makeText(context, R.string.dialog_toast, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val category = Category(
                name = nameCategory,
                emoji = emojiCategory
            )

            editCategoryDialogListener.editCategory(category)
            dismiss()
        }

        binding.cancelButton.setOnClickListener {
            cancel()
        }
    }
}