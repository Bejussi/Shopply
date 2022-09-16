package com.bejussi.shopply.presentation.adapter.category

import com.bejussi.shopply.domain.model.Category

interface CategoryActionListener {

    fun onCategoryDelete(category: Category)

    fun onCategoryEdit(category: Category)

    fun onShowCategoryProductsList(category: Category)
}