package com.bejussi.shopply.domain.repository

import com.bejussi.shopply.domain.model.Item
import kotlinx.coroutines.flow.Flow


interface ItemRepository {

    fun getItem(itemId: Int): Flow<Item>

    fun getItemsList(categoryName: String): Flow<List<Item>>

    suspend fun editItem(item: Item)

    suspend fun deleteItem(item: Item)

    suspend fun addItem(item: Item)
}