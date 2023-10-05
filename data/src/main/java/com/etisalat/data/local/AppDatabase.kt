package com.etisalat.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.etisalat.domain.model.RecipesResponseItem

@Database(entities = [RecipesResponseItem::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun recipesDao(): RecipesDao

}