package com.etisalat.domain.usecase

import com.etisalat.domain.repository.RecipesRepository

class GetRecipes(private val recipesRepository: RecipesRepository) {

    suspend operator fun invoke() = recipesRepository.getRecipesFromRemote()

}