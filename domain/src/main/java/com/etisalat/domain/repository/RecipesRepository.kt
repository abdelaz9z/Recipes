package com.etisalat.domain.repository

import com.etisalat.domain.model.RecipesResponse
import com.etisalat.domain.model.RecipesResponseItem
import kotlinx.coroutines.flow.Flow

/**
 * Interface defining the contract for retrieving recipes.
 */
interface RecipesRepository {

    /**
     * Retrieves recipes from a remote data source.
     *
     * @return [RecipesResponse] object containing the list of recipes.
     */
    suspend fun getRecipesFromRemote(): RecipesResponse

    /**
     * Retrieves recipes from a local data source.
     *
     * @return [Flow] emitting a list of [RecipesResponseItem] objects.
     */
    fun getRecipesFromLocal(): Flow<List<RecipesResponseItem>>

    /**
     * Inserts a new [RecipesResponse] into the local data source.
     *
     * @param recipesResponse The [RecipesResponse] to be inserted.
     */
    fun insertRecipesItem(recipesResponse: RecipesResponse)
}