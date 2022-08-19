package com.bejussi.shopply.domain.repository

import com.bejussi.shopply.domain.model.Category

interface CategoryRepository {

    fun getCategory(categoryName: String): Category

    fun getCategoryList(): List<Category>

    fun editCategory(category: Category)

    fun deleteCategory(category: Category)

    fun addCategory(category: Category)
}