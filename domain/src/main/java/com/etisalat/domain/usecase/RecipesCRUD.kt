package com.etisalat.domain.usecase

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
     * Inserts a new [RecipesResponseItem] into the local data source.
     *
     * @param recipesResponseItem The [RecipesResponseItem] to be inserted.
     */
    fun insertRecipesItem(recipesResponseItem: RecipesResponseItem) {
        recipesRepository.insertRecipesItem(recipesResponseItem)
    }

}