package com.bejussi.shopply.domain.use_case.item

import com.bejussi.shopply.domain.model.Item
import com.bejussi.shopply.domain.repository.ItemRepository
import kotlinx.coroutines.flow.Flow

class SortByNameUseCase(private val itemRepository: ItemRepository) {

    operator fun invoke(categoryId: Int): Flow<List<Item>> {
        return itemRepository.sortByName(categoryId)
    }
}