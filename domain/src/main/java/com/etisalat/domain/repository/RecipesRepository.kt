package com.etisalat.domain.repository

import com.etisalat.domain.model.RecipesResponse
import com.etisalat.domain.model.RecipesResponseItem
import kotlinx.coroutines.flow.Flow

interface RecipesRepository {
    suspend fun getRecipesFromRemote(): RecipesResponse

    fun getRecipesFromLocal(): Flow<List<RecipesResponseItem>>

    fun insertRecipesItem(recipesResponseItem: RecipesResponseItem)
}