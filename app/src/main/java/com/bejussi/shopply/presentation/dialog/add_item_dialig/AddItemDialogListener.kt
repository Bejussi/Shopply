package com.bejussi.shopply.presentation.dialog.add_item_dialig

import com.bejussi.shopply.domain.model.Item

interface AddItemDialogListener {

    fun addItem(item: Item)
}