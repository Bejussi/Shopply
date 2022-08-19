package com.bejussi.shopply.domain.use_case.category

import com.bejussi.shopply.domain.model.Category
import com.bejussi.shopply.domain.repository.CategoryRepository

class EditCategoryUseCase(private val categoryRepository: CategoryRepository) {

    fun editCategory(category: Category) {
        categoryRepository.editCategory(category)
    }
}