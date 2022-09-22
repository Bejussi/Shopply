package com.bejussi.shopply.presentation.adapter.item

import androidx.recyclerview.widget.RecyclerView
import com.bejussi.shopply.R
import com.bejussi.shopply.databinding.ItemItemsCategoryBinding
import com.bejussi.shopply.databinding.ItemItemsCategoryBoughtBinding
import com.bejussi.shopply.domain.model.Item

class ItemViewHolder(
    val binding: ItemItemsCategoryBinding
): RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Item) {
        binding.apply {
            itemNameText.text = item.name
            countText.text = itemView.context.getString(R.string.count_item, item.count)
            checkBox.isChecked = item.bought
            itemPriceText.text = itemView.context.getString(R.string.price_item, item.price)
            itemTotalText.text = itemView.context.getString(R.string.total_item, (item.price * item.count))
        }
    }
}

class ItemBoughtViewHolder(
    val binding: ItemItemsCategoryBoughtBinding
): RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Item) {
        binding.apply {
            itemNameText.text = item.name
            countText.text = itemView.context.getString(R.string.count_item, item.count)
            checkBox.isChecked = item.bought
            itemPriceText.text = itemView.context.getString(R.string.price_item, item.price)
            itemTotalText.text = itemView.context.getString(R.string.total_item, (item.price * item.count))
        }
    }
}