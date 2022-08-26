package com.bejussi.shopply.data.data_source

import androidx.room.*
import com.bejussi.shopply.domain.model.Category
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {

    @Query("SELECT * FROM category")
    fun getCategoryList(): Flow<List<Category>>

    @Query("SELECT * FROM category WHERE name = :name")
    fun getCategory(name: String): Flow<Category>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCategory(category: Category)

    @Update
    suspend fun editCategory(category: Category)

    @Delete
    suspend fun deleteCategory(category: Category)
}