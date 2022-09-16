package com.bejussi.shopply.domain.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class Category(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var name: String
): Parcelable
