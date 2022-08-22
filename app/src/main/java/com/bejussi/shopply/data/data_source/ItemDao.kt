package com.bejussi.shopply.data.data_source

import androidx.room.*
import com.bejussi.shopply.domain.model.Item
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {

    @Query("SELECT* FROM item WHERE category = :categoryName")
    fun getItemsList(categoryName: String): Flow<List<Item>>

    @Query("SELECT* FROM item WHERE id = :id")
    suspend fun getItem(id: Int): Item

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addItem(item: Item)

    @Update
    suspend fun editItem(item: Item)

    @Delete
    suspend fun deleteItem(item: Item)
}