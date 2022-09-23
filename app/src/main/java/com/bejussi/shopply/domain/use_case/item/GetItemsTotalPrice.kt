package com.bejussi.shopply.domain.use_case.item

import com.bejussi.shopply.domain.repository.ItemRepository
import kotlinx.coroutines.flow.Flow

class GetItemsTotalPrice(private val itemRepository: ItemRepository) {

    operator fun invoke(categoryId: Int): Flow<Float> {
        return itemRepository.getItemsTotalPrice(categoryId)
    }
}