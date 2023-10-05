package com.etisalat.domain.usecase

import com.etisalat.domain.repository.RecipesRepository

/**
 * Use case class for retrieving recipes.
 *
 * @param recipesRepository The repository for accessing recipe data.
 */
class GetRecipes(private val recipesRepository: RecipesRepository) {

    /**
     * Invokes the use case to retrieve recipes from the remote data source.
     *
     *@return [RecipesResponse] object containing the list of recipes.
     */
    suspend operator fun invoke() = recipesRepository.getRecipesFromRemote()

}