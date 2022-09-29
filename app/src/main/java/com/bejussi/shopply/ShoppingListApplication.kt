package com.bejussi.shopply

import android.content.Context
import com.akexorcist.localizationactivity.ui.LocalizationApplication
import dagger.hilt.android.HiltAndroidApp
import java.util.*

@HiltAndroidApp
class ShoppingListApplication: LocalizationApplication() {
    override fun getDefaultLanguage(context: Context) = Locale.ENGLISH
}