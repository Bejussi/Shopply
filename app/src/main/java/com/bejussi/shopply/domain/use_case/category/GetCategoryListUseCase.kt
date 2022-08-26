package com.bejussi.shopply.domain.use_case.category

import com.bejussi.shopply.domain.model.Category
import com.bejussi.shopply.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow

class GetCategoryListUseCase(private val categoryRepository: CategoryRepository) {

    operator fun invoke(): Flow<List<Category>> {
        return categoryRepository.getCategoryList()
    }
}