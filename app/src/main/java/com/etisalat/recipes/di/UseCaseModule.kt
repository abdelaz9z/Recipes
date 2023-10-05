package com.etisalat.recipes.di

import com.etisalat.domain.repository.RecipesRepository
import com.etisalat.domain.usecase.GetRecipes
import com.etisalat.domain.usecase.RecipesCRUD
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideRecipesRepositoryUseCase(recipesRepository: RecipesRepository): GetRecipes {
        return GetRecipes(recipesRepository)
    }

    @Singleton
    @Provides
    fun provideUserCRUD(recipesRepository: RecipesRepository): RecipesCRUD {
        return RecipesCRUD(recipesRepository)
    }
}