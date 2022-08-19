package com.bejussi.shopply.domain.repository

import com.bejussi.shopply.domain.model.Item

interface ItemRepository {

    fun getItem(itemId: Int): Item

    fun getItemsList(categoryName: String): List<Item>

    fun editItem(item: Item)

    fun deleteItem(item: Item)

    fun addItem(item: Item)
}