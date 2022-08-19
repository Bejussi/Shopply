package com.bejussi.shopply.domain.use_case.item

import com.bejussi.shopply.domain.model.Item
import com.bejussi.shopply.domain.repository.ItemRepository

class DeleteItemUseCase(private val itemRepository: ItemRepository) {

    fun deleteItem(item: Item) {
        itemRepository.deleteItem(item)
    }
}