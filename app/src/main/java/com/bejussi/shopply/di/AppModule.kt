package com.bejussi.shopply.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.bejussi.shopply.data.data_source.ShoppingListDatabase
import com.bejussi.shopply.data.repository.CategoryRepositoryImpl
import com.bejussi.shopply.data.repository.ItemRepositoryImpl
import com.bejussi.shopply.domain.repository.CategoryRepository
import com.bejussi.shopply.domain.repository.ItemRepository
import com.bejussi.shopply.domain.use_case.CategoryUseCases
import com.bejussi.shopply.domain.use_case.ItemUseCases
import com.bejussi.shopply.domain.use_case.category.*
import com.bejussi.shopply.domain.use_case.item.*
import com.bejussi.shopply.presentation.utils.SettingsDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideShoppingListDatabase(app: Application): ShoppingListDatabase {
        return Room.databaseBuilder(
            app,
            ShoppingListDatabase::class.java,
            ShoppingListDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideCategoryRepository(db: ShoppingListDatabase): CategoryRepository {
        return CategoryRepositoryImpl(db.categoryDao())
    }

    @Provides
    @Singleton
    fun provideItemRepository(db: ShoppingListDatabase): ItemRepository {
        return ItemRepositoryImpl(db.itemDao())
    }

    @Provides
    @Singleton
    fun provideCategoryUseCases(repository: CategoryRepository): CategoryUseCases {
        return CategoryUseCases(
            getCategoryUseCase = GetCategoryUseCase(repository),
            getCategoryListUseCase = GetCategoryListUseCase(repository),
            editCategoryUseCase = EditCategoryUseCase(repository),
            deleteCategoryUseCase = DeleteCategoryUseCase(repository),
            addCategoryUseCase = AddCategoryUseCase(repository),
            searchCategoryUseCase = SearchCategoryUseCase(repository)
        )
    }

    @Provides
    @Singleton
    fun provideItemUseCases(repository: ItemRepository): ItemUseCases {
        return ItemUseCases(
            getItemUseCase = GetItemUseCase(repository),
            getItemsListUseCase = GetItemsListUseCase(repository),
            editItemUseCase = EditItemUseCase(repository),
            deleteItemUseCase = DeleteItemUseCase(repository),
            addItemUseCase = AddItemUseCase(repository),
            cleanItemsListUseCase = CleanItemsListUseCase(repository),
            deleteCheckedItemsUseCase = DeleteCheckedItemsUseCase(repository),
            sortByNameUseCase = SortByNameUseCase(repository),
            getItemsTotalPrice = GetItemsTotalPrice(repository)
        )
    }

    @Singleton
    @Provides
    fun provideSettingsDataStore(@ApplicationContext appContext: Context): SettingsDataStore =
        SettingsDataStore(appContext)
}