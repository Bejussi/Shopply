package com.bejussi.shopply.domain.use_case.category

import com.bejussi.shopply.domain.model.Category
import com.bejussi.shopply.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow

class SearchCategoryUseCase(private val categoryRepository: CategoryRepository) {

    operator fun invoke(search: String): Flow<List<Category>> {
        return categoryRepository.searchQuery(search)
    }

}