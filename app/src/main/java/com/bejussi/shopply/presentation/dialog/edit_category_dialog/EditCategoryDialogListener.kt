package com.bejussi.shopply.presentation.dialog.edit_category_dialog

import com.bejussi.shopply.domain.model.Category

interface EditCategoryDialogListener {

    fun editCategory(category: Category)
}