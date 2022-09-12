package com.bejussi.shopply.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class Category(
    @PrimaryKey(autoGenerate = false)
    val name: String,
    val emoji: String
): Parcelable
