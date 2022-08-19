package com.bejussi.shopply.domain.use_case.item

import com.bejussi.shopply.domain.model.Item
import com.bejussi.shopply.domain.repository.ItemRepository

class GetItemUseCase(private val itemRepository: ItemRepository) {

    fun getItem(itemId: Int): Item {
        return itemRepository.getItem(itemId)
    }
}