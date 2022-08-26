package com.bejussi.shopply.domain.use_case.category

import com.bejussi.shopply.domain.model.Category
import com.bejussi.shopply.domain.repository.CategoryRepository

class AddCategoryUseCase(private val categoryRepository: CategoryRepository) {

    suspend operator fun invoke(category: Category) {
        categoryRepository.addCategory(category)
    }
}