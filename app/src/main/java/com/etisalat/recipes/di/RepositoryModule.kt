package com.etisalat.recipes.di

import android.content.Context
import androidx.room.Room
import com.etisalat.data.local.AppDatabase
import com.etisalat.data.local.RecipesDao
import com.etisalat.data.remote.ApiService
import com.etisalat.data.repository.RecipesRepositoryImpl
import com.etisalat.domain.repository.RecipesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideRecipesRepository(
        apiService: ApiService,
        recipesDao: RecipesDao
    ): RecipesRepository {
        return RecipesRepositoryImpl(apiService, recipesDao)
    }

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "recipes_database"
        ).build()

    @Singleton
    @Provides
    fun provideRecipesDao(appDatabase: AppDatabase) = appDatabase.recipesDao()
}