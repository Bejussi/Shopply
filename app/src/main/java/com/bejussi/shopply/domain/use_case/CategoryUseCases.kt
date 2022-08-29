package com.bejussi.shopply.domain.use_case

import com.bejussi.shopply.domain.use_case.category.*

data class CategoryUseCases(
    val getCategoryListUseCase: GetCategoryListUseCase,
    val getCategoryUseCase: GetCategoryUseCase,
    val editCategoryUseCase: EditCategoryUseCase,
    val deleteCategoryUseCase: DeleteCategoryUseCase,
    val addCategoryUseCase: AddCategoryUseCase,
    val searchCategoryUseCase: SearchCategoryUseCase
)
