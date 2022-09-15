package com.bejussi.shopply.data.data_source

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bejussi.shopply.domain.model.Category
import com.bejussi.shopply.domain.model.Item

@Database(
    entities = [Item::class, Category::class],
    version = 4,
    exportSchema = false
)
abstract class ShoppingListDatabase: RoomDatabase() {

    abstract fun categoryDao(): CategoryDao
    abstract fun itemDao(): ItemDao

    companion object {
        @Volatile
        private var INSTANCE: ShoppingListDatabase? = null

        const val DATABASE_NAME = "shopping_list_database"

        fun getDatabase(context: Context): ShoppingListDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ShoppingListDatabase::class.java,
                    DATABASE_NAME
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}