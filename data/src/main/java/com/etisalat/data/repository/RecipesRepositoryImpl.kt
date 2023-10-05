package com.etisalat.data.repository

import com.etisalat.data.local.RecipesDao
import com.etisalat.data.remote.ApiService
import com.etisalat.domain.model.RecipesResponse
import com.etisalat.domain.model.RecipesResponseItem
import com.etisalat.domain.repository.RecipesRepository
import kotlinx.coroutines.flow.Flow

class RecipesRepositoryImpl(
    private val apiService: ApiService,
    private val recipesDao: RecipesDao
) : RecipesRepository {

    override suspend fun getRecipesFromRemote(): RecipesResponse = apiService.getRecipes()

    override fun getRecipesFromLocal(): Flow<List<RecipesResponseItem>> = recipesDao.getRecipes()

    override fun insertRecipesItem(recipesResponseItem: RecipesResponseItem) {
        recipesDao.insertRecipes(recipesResponseItem)
    }
}