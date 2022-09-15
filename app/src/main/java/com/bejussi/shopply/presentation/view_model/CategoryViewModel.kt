package com.bejussi.shopply.presentation.view_model

import androidx.lifecycle.*
import com.bejussi.shopply.domain.model.Category
import com.bejussi.shopply.domain.use_case.CategoryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val categoryUseCases: CategoryUseCases
) : ViewModel() {

    val categoryList: LiveData<List<Category>> =
        categoryUseCases.getCategoryListUseCase().asLiveData()

    val emptyDatabase: MutableLiveData<Boolean> = MutableLiveData(true)

    fun checkIfDatabaseEmpty(category: List<Category>) {
        emptyDatabase.value = category.isEmpty()
    }

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

    fun searchCategory(search: String): LiveData<List<Category>> {
        return categoryUseCases.searchCategoryUseCase(search).asLiveData()
    }
}