package com.bejussi.shopply.domain.use_case

import com.bejussi.shopply.domain.use_case.item.*

data class ItemUseCases(
    val getItemsListUseCase: GetItemsListUseCase,
    val getItemUseCase: GetItemUseCase,
    val editItemUseCase: EditItemUseCase,
    val deleteItemUseCase: DeleteItemUseCase,
    val addItemUseCase: AddItemUseCase,
    val cleanItemsListUseCase: CleanItemsListUseCase,
    val deleteCheckedItemsUseCase: DeleteCheckedItemsUseCase,
    val sortByNameUseCase: SortByNameUseCase,
    val getItemsTotalPrice: GetItemsTotalPrice
)