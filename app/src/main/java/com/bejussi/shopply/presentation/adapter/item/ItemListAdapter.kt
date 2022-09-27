package com.bejussi.shopply.presentation.adapter.item

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bejussi.shopply.databinding.ItemItemsCategoryBinding
import com.bejussi.shopply.databinding.ItemItemsCategoryBoughtBinding
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

        when (holder) {
            is ItemBoughtViewHolder -> holder.apply {
                binding.apply {
                    checkBox.setOnClickListener {
                        currentItem.bought = checkBox.isChecked
                        itemActionListener.onItemEdit(currentItem)
                    }
                    expandable.visibility = if(currentItem.isExpandable) View.VISIBLE else View.GONE
                }
                itemView.setOnClickListener {
                    currentItem.isExpandable = !currentItem.isExpandable
                    itemActionListener.onItemEdit(currentItem)
                }
            }.bind(currentItem)

            is ItemViewHolder -> holder.apply {
                binding.apply {
                    plusButton.setOnClickListener {
                        currentItem.count = currentItem.count + 0.1f
                        itemActionListener.onItemEdit(currentItem)
                    }

                    minusButton.setOnClickListener {
                        if (currentItem.count > 0.1) {
                            currentItem.count = currentItem.count - 0.1f
                            itemActionListener.onItemEdit(currentItem)
                        }
                    }
                    checkBox.setOnClickListener {
                        currentItem.bought = checkBox.isChecked
                        itemActionListener.onItemEdit(currentItem)
                    }
                    countText.setOnClickListener {
                        itemActionListener.showOnItemEditFragment(currentItem)
                    }
                    expandable.visibility = if(currentItem.isExpandable) View.VISIBLE else View.GONE
                }
                itemView.setOnClickListener {
                    currentItem.isExpandable = !currentItem.isExpandable
                    itemActionListener.onItemEdit(currentItem)
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
                return oldItem.id == newItem.id
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem === newItem
            }
        }

        const val ITEM_BOUGHT = 0
        const val ITEM_DONT_BOUGHT = 1
    }
}