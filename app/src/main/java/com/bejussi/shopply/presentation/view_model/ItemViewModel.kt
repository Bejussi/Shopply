package com.bejussi.shopply.presentation.view_model

import android.util.Log
import androidx.lifecycle.*
import androidx.navigation.navArgument
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

    val categoryName = state.get<String>(CATEGORY_STATE_KEY)!!

    val allItems: LiveData<List<Item>> = itemUseCases.getItemsListUseCase(categoryName).asLiveData()
    val sortedItems: LiveData<List<Item>> = itemUseCases.sortByNameUseCase(categoryName).asLiveData()

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

    fun deleteCheckedItems(categoryName: String) {
        viewModelScope.launch {
            itemUseCases.deleteCheckedItemsUseCase(categoryName)
        }
    }

    fun cleanItemsList(categoryName: String) {
        viewModelScope.launch {
            itemUseCases.cleanItemsListUseCase(categoryName)
        }
    }

    companion object {
        private const val CATEGORY_STATE_KEY = "categoryName"
    }
}