package com.bejussi.shopply.domain.use_case.item

import com.bejussi.shopply.domain.model.Item
import com.bejussi.shopply.domain.repository.ItemRepository

class EditItemUseCase(private val itemRepository: ItemRepository) {

    suspend operator fun invoke(item: Item) {
        itemRepository.editItem(item)
    }
}