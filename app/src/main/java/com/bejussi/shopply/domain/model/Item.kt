package com.bejussi.shopply.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(foreignKeys = [ForeignKey(
    entity = Category::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("categoryId"),
    onDelete = ForeignKey.CASCADE,
    onUpdate = ForeignKey.CASCADE
)])
@Parcelize
data class Item(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    var count: Float,
    val categoryId: Int,
    var bought: Boolean,
    var price: Float,
    var isExpandable: Boolean
): Parcelable
