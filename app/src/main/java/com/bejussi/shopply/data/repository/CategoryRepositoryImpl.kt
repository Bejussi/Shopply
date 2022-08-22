package com.bejussi.shopply.data.repository

import com.bejussi.shopply.data.data_source.CategoryDao
import com.bejussi.shopply.domain.model.Category
import com.bejussi.shopply.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow

class CategoryRepositoryImpl(
    private val categoryDao: CategoryDao
): CategoryRepository {

    override suspend fun getCategory(categoryName: String): Category {
        return categoryDao.getCategory(categoryName)
    }

    override fun getCategoryList(): Flow<List<Category>> {
        return categoryDao.getCategoryList()
    }

    override suspend fun editCategory(category: Category) {
        categoryDao.editCategory(category)
    }

    override suspend fun deleteCategory(category: Category) {
        categoryDao.deleteCategory(category)
    }

    override suspend fun addCategory(category: Category) {
        categoryDao.addCategory(category)
    }
}