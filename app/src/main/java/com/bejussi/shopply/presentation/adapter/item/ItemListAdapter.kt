package com.bejussi.shopply.presentation.adapter.item

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bejussi.shopply.databinding.ItemItemsCategoryBinding
import com.bejussi.shopply.domain.model.Category
import com.bejussi.shopply.domain.model.Item

class ItemListAdapter(
    private val itemActionListener: ItemActionListener
): ListAdapter<Item, ItemListAdapter.ItemViewHolder>(DiffCallback) {

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
           ItemItemsCategoryBinding.inflate(
               LayoutInflater.from(
                   parent.context
               ), parent, false
           )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentItem = getItem(position)

        with(holder.binding) {
            itemNameText.text = currentItem.name
            countText.text = currentItem.count.toString()
            checkBox.isChecked = currentItem.bought

            plusButton.setOnClickListener {
                currentItem.count++
                itemActionListener.onItemEdit(currentItem)
            }

            minusButton.setOnClickListener {
                if (currentItem.count > 1) {
                    currentItem.count--
                    itemActionListener.onItemEdit(currentItem)
                }
            }

            checkBox.setOnClickListener {
                currentItem.bought = checkBox.isChecked
                itemActionListener.onItemEdit(currentItem)
            }
        }
        holder.bind(currentItem)
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Item>() {
            override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem.name == newItem.name
            }
        }
    }
}