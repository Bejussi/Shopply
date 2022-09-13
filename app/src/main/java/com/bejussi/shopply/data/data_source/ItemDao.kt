package com.bejussi.shopply.data.data_source

import androidx.room.*
import com.bejussi.shopply.domain.model.Item
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {

    @Query("SELECT* FROM item WHERE category = :categoryName ORDER BY bought")
    fun getItemsList(categoryName: String): Flow<List<Item>>

    @Query("SELECT* FROM item WHERE id = :id")
    fun getItem(id: Int): Flow<Item>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addItem(item: Item)

    @Update
    suspend fun editItem(item: Item)

    @Delete
    suspend fun deleteItem(item: Item)

    @Query("DELETE FROM item WHERE category = :categoryName")
    suspend fun cleanItemsList(categoryName: String)

    @Query("DELETE FROM item WHERE category = :categoryName AND bought = 1")
    suspend fun deleteCheckedItems(categoryName: String)

    @Query("SELECT* FROM item WHERE category = :categoryName ORDER BY name")
    fun sortByName(categoryName: String): Flow<List<Item>>
}