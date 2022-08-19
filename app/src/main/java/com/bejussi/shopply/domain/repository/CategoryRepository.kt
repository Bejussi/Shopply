package com.bejussi.shopply.domain.repository

import com.bejussi.shopply.domain.model.Category
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {

    suspend fun getCategory(categoryName: String): Category

    fun getCategoryList(): Flow<List<Category>>

    suspend fun editCategory(category: Category)

    suspend fun deleteCategory(category: Category)

    suspend fun addCategory(category: Category)
}