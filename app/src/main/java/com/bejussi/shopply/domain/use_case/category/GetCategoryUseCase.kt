package com.bejussi.shopply.domain.use_case.category

import com.bejussi.shopply.domain.model.Category
import com.bejussi.shopply.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow

class GetCategoryUseCase(private val categoryRepository: CategoryRepository) {

    operator fun invoke(categoryName: String): Flow<Category> {
        return categoryRepository.getCategory(categoryName)
    }
}