package com.bejussi.shopply.presentation.view_model

import com.bejussi.shopply.domain.model.Category

interface CategoryActionListener {

    fun onCategoryDelete(category: Category)

    fun onCategoryEdit(category: Category)

    fun onShowCategoryProductsList(categoryName: String)
}