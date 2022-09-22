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

    override fun getItemsList(categoryId: Int): Flow<List<Item>> {
        return itemDao.getItemsList(categoryId)
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

    override suspend fun cleanItemsList(categoryId: Int) {
        itemDao.cleanItemsList(categoryId)
    }

    override suspend fun deleteCheckedItems(categoryId: Int) {
        itemDao.deleteCheckedItems(categoryId)
    }

    override fun sortByName(categoryId: Int): Flow<List<Item>> {
        return itemDao.sortByName(categoryId)
    }
}