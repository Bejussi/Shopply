package com.bejussi.shopply.presentation.dialog.add_category_dialog

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import com.bejussi.shopply.R
import com.bejussi.shopply.databinding.DialogAddCategoryBinding
import com.bejussi.shopply.domain.model.Category

class AddCategoryDialog(
    context: Context,
    private val addCategoryDialogListener: AddCategoryDialogListener
): AppCompatDialog(context) {

    private var _binding: DialogAddCategoryBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = DialogAddCategoryBinding.inflate(LayoutInflater.from(context))
        setContentView(binding.root)
        window
            ?.setLayout(
                ViewGroup.LayoutParams.FILL_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )

        binding.addButton.setOnClickListener {
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

            addCategoryDialogListener.addCategory(category)
            dismiss()
        }

        binding.cancelButton.setOnClickListener {
            cancel()
        }
    }
}