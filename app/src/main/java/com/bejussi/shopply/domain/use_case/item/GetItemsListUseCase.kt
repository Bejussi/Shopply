package com.bejussi.shopply.domain.use_case.item

import com.bejussi.shopply.domain.model.Item
import com.bejussi.shopply.domain.repository.ItemRepository
import kotlinx.coroutines.flow.Flow

class GetItemsListUseCase(private val itemRepository: ItemRepository) {

    operator fun invoke(categoryId: Int): Flow<List<Item>> {
        return itemRepository.getItemsList(categoryId)
    }
}