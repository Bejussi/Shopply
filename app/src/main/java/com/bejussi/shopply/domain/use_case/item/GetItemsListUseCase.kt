package com.bejussi.shopply.domain.use_case.item

import com.bejussi.shopply.domain.model.Item
import com.bejussi.shopply.domain.repository.ItemRepository

class GetItemsListUseCase(private val itemRepository: ItemRepository) {

    fun getItemsList(categoryName: String): List<Item> {
        return itemRepository.getItemsList(categoryName)
    }
}