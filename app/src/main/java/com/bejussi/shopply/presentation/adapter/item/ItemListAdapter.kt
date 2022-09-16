package com.bejussi.shopply.presentation.adapter.item

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bejussi.shopply.R
import com.bejussi.shopply.databinding.ItemItemsCategoryBinding
import com.bejussi.shopply.databinding.ItemItemsCategoryBoughtBinding
import com.bejussi.shopply.domain.model.Category
import com.bejussi.shopply.domain.model.Item

class ItemListAdapter(
    private val itemActionListener: ItemActionListener
) : ListAdapter<Item, RecyclerView.ViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_BOUGHT -> ItemBoughtViewHolder(
                ItemItemsCategoryBoughtBinding.inflate(
                    LayoutInflater.from(
                        parent.context
                    ), parent, false
                )
            )
            ITEM_DONT_BOUGHT -> ItemViewHolder(
                ItemItemsCategoryBinding.inflate(
                    LayoutInflater.from(
                        parent.context
                    ), parent, false
                )
            )
            else -> throw RuntimeException("Unknow view type: $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentItem = getItem(position)

        when (getItemViewType(position)) {
            ITEM_BOUGHT -> (holder as ItemBoughtViewHolder).apply {
                binding.apply {
                    checkBox.setOnClickListener {
                        currentItem.bought = checkBox.isChecked
                        itemActionListener.onItemEdit(currentItem)
                    }
                }
            }.bind(currentItem)

            ITEM_DONT_BOUGHT -> (holder as ItemViewHolder).apply {
                binding.apply {
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
            }.bind(currentItem)
        }
    }

    override fun getItemViewType(position: Int): Int {
        val currentItem = getItem(position)
        return if (currentItem.bought) {
            ITEM_BOUGHT
        } else {
            ITEM_DONT_BOUGHT
        }
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

        const val ITEM_BOUGHT = 0
        const val ITEM_DONT_BOUGHT = 1
    }
}