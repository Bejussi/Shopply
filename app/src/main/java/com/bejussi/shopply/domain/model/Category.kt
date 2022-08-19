package com.bejussi.shopply.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Category(
    @PrimaryKey(autoGenerate = false)
    val name: String
)
