package com.bejussi.shopply

import android.app.Application
import com.bejussi.shopply.data.data_source.ShoppingListDatabase

class ShoppingListApplication: Application() {
    val database: ShoppingListDatabase by lazy { ShoppingListDatabase.getDatabase(this) }
}