package com.bejussi.shopply.domain.use_case.item

import com.bejussi.shopply.domain.repository.ItemRepository

class CleanItemsListUseCase(private val itemRepository: ItemRepository) {
    
    suspend operator fun invoke(categoryName: String) {
        itemRepository.cleanItemsList(categoryName)
    }
}