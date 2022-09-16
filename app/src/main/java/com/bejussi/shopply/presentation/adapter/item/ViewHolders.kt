package com.bejussi.shopply.presentation.adapter.item

import androidx.recyclerview.widget.RecyclerView
import com.bejussi.shopply.databinding.ItemItemsCategoryBinding
import com.bejussi.shopply.databinding.ItemItemsCategoryBoughtBinding
import com.bejussi.shopply.domain.model.Item

class ItemViewHolder(
    val binding: ItemItemsCategoryBinding
): RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Item) {
        binding.apply {
            itemNameText.text = item.name
            countText.text = item.count.toString()
            checkBox.isChecked = item.bought
        }
    }
}

class ItemBoughtViewHolder(
    val binding: ItemItemsCategoryBoughtBinding
): RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Item) {
        binding.apply {
            itemNameText.text = item.name
            countText.text = item.count.toString()
            checkBox.isChecked = item.bought
        }
    }
}