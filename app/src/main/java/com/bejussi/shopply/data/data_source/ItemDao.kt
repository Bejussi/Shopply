package com.bejussi.shopply.data.data_source

import androidx.room.*
import com.bejussi.shopply.domain.model.Item
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {

    @Query("SELECT* FROM item WHERE categoryId = :categoryId ORDER BY bought")
    fun getItemsList(categoryId: Int): Flow<List<Item>>

    @Query("SELECT* FROM item WHERE id = :id")
    fun getItem(id: Int): Flow<Item>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addItem(item: Item)

    @Update
    suspend fun editItem(item: Item)

    @Delete
    suspend fun deleteItem(item: Item)

    @Query("DELETE FROM item WHERE categoryId = :categoryId")
    suspend fun cleanItemsList(categoryId: Int)

    @Query("DELETE FROM item WHERE categoryId = :categoryId AND bought = 1")
    suspend fun deleteCheckedItems(categoryId: Int)

    @Query("SELECT* FROM item WHERE categoryId = :categoryId ORDER BY name")
    fun sortByName(categoryId: Int): Flow<List<Item>>
}