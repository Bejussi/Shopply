package com.bejussi.shopply.domain.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(foreignKeys = [ForeignKey(
    entity = Category::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("categoryId"),
    onDelete = ForeignKey.CASCADE,
    onUpdate = ForeignKey.CASCADE
)])
data class Item(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    var count: Int,
    val category: String,
    val categoryId: Int,
    var bought: Boolean
)
