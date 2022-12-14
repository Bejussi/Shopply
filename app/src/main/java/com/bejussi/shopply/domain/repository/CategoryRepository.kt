package com.bejussi.shopply.domain.repository

import com.bejussi.shopply.domain.model.Category
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {

    fun getCategory(categoryName: String): Flow<Category>

    fun getCategoryList(): Flow<List<Category>>

    suspend fun editCategory(category: Category)

    suspend fun deleteCategory(category: Category)

    suspend fun addCategory(category: Category)

    fun searchQuery(search: String): Flow<List<Category>>
}