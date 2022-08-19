package com.bejussi.shopply.domain.use_case.category

import com.bejussi.shopply.domain.model.Category
import com.bejussi.shopply.domain.repository.CategoryRepository

class GetCategoryUseCase(private val categoryRepository: CategoryRepository) {

    fun getCategory(categoryName: String): Category {
        return categoryRepository.getCategory(categoryName)
    }
}