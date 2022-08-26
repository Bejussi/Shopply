package com.bejussi.shopply.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bejussi.shopply.R
import com.bejussi.shopply.databinding.ItemCategoryBinding
import com.bejussi.shopply.domain.model.Category
import com.bejussi.shopply.presentation.view_model.CategoryViewModel

class CategoryListAdapter(
    private val onItemClicked: (Category) -> Unit
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

            }
        }

        holder.itemView.setOnClickListener {
            onItemClicked(currentCategory)
        }

        holder.bind(currentCategory)
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