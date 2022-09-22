package com.bejussi.shopply.domain.repository

import com.bejussi.shopply.domain.model.Item
import kotlinx.coroutines.flow.Flow


interface ItemRepository {

    fun getItem(itemId: Int): Flow<Item>

    fun getItemsList(categoryId: Int): Flow<List<Item>>

    suspend fun editItem(item: Item)

    suspend fun deleteItem(item: Item)

    suspend fun addItem(item: Item)

    suspend fun cleanItemsList(categoryId: Int)

    suspend fun deleteCheckedItems(categoryId: Int)

    fun sortByName(categoryId: Int): Flow<List<Item>>
}