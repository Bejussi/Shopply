package com.bejussi.shopply.presentation.view_model

import android.util.Log
import androidx.lifecycle.*
import androidx.navigation.navArgument
import com.bejussi.shopply.domain.model.Category
import com.bejussi.shopply.domain.model.Item
import com.bejussi.shopply.domain.use_case.ItemUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemViewModel @Inject constructor(
    private val itemUseCases: ItemUseCases,
    private val state: SavedStateHandle
): ViewModel() {

    val category = state.get<Category>(CATEGORY_STATE_KEY)!!

    val allItems: LiveData<List<Item>> = itemUseCases.getItemsListUseCase(category.id).asLiveData()
    val sortedItems: LiveData<List<Item>> = itemUseCases.sortByNameUseCase(category.id).asLiveData()

    val totalPrice: LiveData<Float> = itemUseCases.getItemsTotalPrice(category.id).asLiveData()

    val emptyDatabase: MutableLiveData<Boolean> = MutableLiveData(false)

    fun checkIfDatabaseEmpty(items: List<Item>) {
        emptyDatabase.value = items.isEmpty()
    }

    fun insertItem(item: Item) {
        viewModelScope.launch {
            itemUseCases.addItemUseCase(item)
        }
    }

    fun deleteItem(item: Item) {
        viewModelScope.launch {
            itemUseCases.deleteItemUseCase(item)
        }
    }

    fun editItem(item: Item) {
        viewModelScope.launch {
            itemUseCases.editItemUseCase(item)
        }
    }

    fun deleteCheckedItems(categoryId: Int) {
        viewModelScope.launch {
            itemUseCases.deleteCheckedItemsUseCase(categoryId)
        }
    }

    fun cleanItemsList(categoryId: Int) {
        viewModelScope.launch {
            itemUseCases.cleanItemsListUseCase(categoryId)
        }
    }

    companion object {
        private const val CATEGORY_STATE_KEY = "category"
    }
}