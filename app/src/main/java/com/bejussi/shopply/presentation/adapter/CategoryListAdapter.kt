package com.bejussi.shopply.presentation.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bejussi.shopply.R
import com.bejussi.shopply.databinding.ItemCategoryBinding
import com.bejussi.shopply.domain.model.Category
import com.bejussi.shopply.presentation.view_model.CategoryActionListener
import com.bejussi.shopply.presentation.view_model.CategoryViewModel

class CategoryListAdapter(
    private val categoryActionListener: CategoryActionListener
): ListAdapter<Category, CategoryListAdapter.CategoryViewHolder>(DiffCallback) {

    class CategoryViewHolder(
        val binding: ItemCategoryBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(category: Category) {
            binding.apply {
                categoryNameText.text = category.name
                categoryEmojiImage.text = category.emoji
                countItemsText.text = "12 items"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            ItemCategoryBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val currentCategory = getItem(position)
        with(holder.binding) {
            categoryNameText.text = currentCategory.name
            categoryEmojiImage.text = currentCategory.emoji
            menuButton.setOnClickListener {
                popupMenu(it, currentCategory)
            }
        }

        holder.itemView.setOnClickListener {
            categoryActionListener.onShowCategoryProductsList(currentCategory.name)
        }

        holder.bind(currentCategory)
    }

    private fun popupMenu(view: View, category: Category) {
        val popupMenu = PopupMenu(view.context,view)
        popupMenu.inflate(R.menu.item_popup_menu)
        popupMenu.setOnMenuItemClickListener {
            when(it.itemId) {
                R.id.edit_category -> {
                    categoryActionListener.onCategoryEdit(category)
                    true
                }
                R.id.delete_category -> {
                    categoryActionListener.onCategoryDelete(category)
                    true
                }
                else -> false
            }
        }
        popupMenu.setForceShowIcon(true)
        popupMenu.show()
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Category>() {
            override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
                return oldItem.name == newItem.name
            }
        }
    }

}