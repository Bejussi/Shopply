package com.bejussi.shopply.data.repository

import com.bejussi.shopply.data.data_source.CategoryDao
import com.bejussi.shopply.data.data_source.ItemDao
import com.bejussi.shopply.domain.model.Item
import com.bejussi.shopply.domain.repository.ItemRepository
import kotlinx.coroutines.flow.Flow

class ItemRepositoryImpl(
    private val itemDao: ItemDao
): ItemRepository {

    override fun getItem(itemId: Int): Flow<Item> {
        return itemDao.getItem(itemId)
    }

    override fun getItemsList(categoryName: String): Flow<List<Item>> {
        return itemDao.getItemsList(categoryName)
    }

    override suspend fun editItem(item: Item) {
        itemDao.editItem(item)
    }

    override suspend fun deleteItem(item: Item) {
        itemDao.deleteItem(item)
    }

    override suspend fun addItem(item: Item) {
        itemDao.addItem(item)
    }
}