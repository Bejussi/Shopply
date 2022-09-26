package com.bejussi.shopply.presentation.adapter.item

import com.bejussi.shopply.domain.model.Category
import com.bejussi.shopply.domain.model.Item

interface ItemActionListener {

    fun onItemEdit(item: Item)

    fun showOnItemEditFragment(item: Item)
}