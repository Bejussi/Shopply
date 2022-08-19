package com.bejussi.shopply.domain.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(foreignKeys = [ForeignKey(
    entity = Category::class,
    parentColumns = arrayOf("name"),
    childColumns = arrayOf("category"),
    onDelete = ForeignKey.CASCADE,
    onUpdate = ForeignKey.CASCADE
)])
data class Item(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val count: Int,
    val category: String,
    val bought: Boolean
)
