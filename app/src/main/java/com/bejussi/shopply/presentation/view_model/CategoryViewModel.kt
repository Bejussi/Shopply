package com.bejussi.shopply.presentation.view_model

import androidx.lifecycle.*
import com.bejussi.shopply.domain.model.Category
import com.bejussi.shopply.domain.model.Item
import com.bejussi.shopply.domain.use_case.CategoryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val categoryUseCases: CategoryUseCases
): ViewModel() {

    val categoryList: LiveData<List<Category>> = categoryUseCases.getCategoryListUseCase().asLiveData()

    fun insertCategory(category: Category) {
        viewModelScope.launch {
            categoryUseCases.addCategoryUseCase(category)
        }
    }

    fun deleteCategory(category: Category) {
        viewModelScope.launch {
            categoryUseCases.deleteCategoryUseCase(category)
        }
    }

    fun editCategory(category: Category) {
        viewModelScope.launch {
            categoryUseCases.editCategoryUseCase(category)
        }
    }

    fun getCategory(name: String) {
        viewModelScope.launch {
            categoryUseCases.getCategoryUseCase(name)
        }
    }
}