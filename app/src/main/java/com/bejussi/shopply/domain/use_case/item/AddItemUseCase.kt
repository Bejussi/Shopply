package com.bejussi.shopply.domain.use_case.item

import com.bejussi.shopply.domain.model.Item
import com.bejussi.shopply.domain.repository.ItemRepository

class AddItemUseCase(private val itemRepository: ItemRepository) {

    fun addItem(item: Item) {
        itemRepository.addItem(item)
    }
}