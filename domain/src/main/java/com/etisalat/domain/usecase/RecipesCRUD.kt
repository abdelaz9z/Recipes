package com.etisalat.domain.usecase

import com.etisalat.domain.model.RecipesResponse
import com.etisalat.domain.model.RecipesResponseItem
import com.etisalat.domain.repository.RecipesRepository

/**
 * Class for performing CRUD operations on recipes.
 *
 * @param recipesRepository The repository for accessing recipe data.
 */
class RecipesCRUD(private val recipesRepository: RecipesRepository) {

    /**
     * Retrieves recipes from the local data source.
     *
     * @return [Flow] emitting a list of [RecipesResponseItem] objects.
     */
    fun getRecipesFromRemote() = recipesRepository.getRecipesFromLocal()

    /**
     * Inserts a new [RecipesResponse] into the local data source.
     *
     * @param recipesResponse The [RecipesResponse] to be inserted.
     */
    fun insertRecipesItem(recipesResponse: RecipesResponse) {
        recipesRepository.insertRecipesItem(recipesResponse)
    }

}