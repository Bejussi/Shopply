package com.bejussi.shopply.presentation.dialog.add_item_dialig

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import com.bejussi.shopply.R
import com.bejussi.shopply.databinding.DialogAddItemBinding
import com.bejussi.shopply.domain.model.Item

class AddItemDialog(
    context: Context,
    private val addItemDialogListener: AddItemDialogListener,
    private val categoryName: String
): AppCompatDialog(context) {

    private var _binding: DialogAddItemBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = DialogAddItemBinding.inflate(LayoutInflater.from(context))
        setContentView(binding.root)
        window
            ?.setLayout(
                ViewGroup.LayoutParams.FILL_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )

        binding.addButton.setOnClickListener {
            val nameItem = binding.nameEditText.text.toString()
            val countItem = binding.countEditText.text.toString()

            if (nameItem.isEmpty()||countItem.isEmpty()) {
                Toast.makeText(context, R.string.dialog_toast, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val item = Item (
                id = 0,
                name = nameItem,
                count = countItem.toInt(),
                bought = false,
                category = categoryName
            )

            addItemDialogListener.addItem(item)
            dismiss()
        }

        binding.cancelButton.setOnClickListener {
            cancel()
        }
    }
}