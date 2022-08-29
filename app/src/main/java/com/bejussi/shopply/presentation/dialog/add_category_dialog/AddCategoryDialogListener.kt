package com.bejussi.shopply.presentation.dialog.add_category_dialog

import com.bejussi.shopply.domain.model.Category

interface AddCategoryDialogListener {

    fun addCategory(category: Category)
}