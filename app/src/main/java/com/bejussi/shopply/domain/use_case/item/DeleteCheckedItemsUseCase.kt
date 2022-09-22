package com.bejussi.shopply.domain.use_case.item

import com.bejussi.shopply.domain.model.Item
import com.bejussi.shopply.domain.repository.ItemRepository
import kotlinx.coroutines.flow.Flow

class DeleteCheckedItemsUseCase(private val itemRepository: ItemRepository) {

    suspend operator fun invoke(categoryId: Int) {
        itemRepository.deleteCheckedItems(categoryId)
    }
}