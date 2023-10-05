package com.etisalat.domain.usecase

import com.etisalat.domain.model.RecipesResponse
import com.etisalat.domain.model.RecipesResponseItem
import com.etisalat.domain.repository.RecipesRepository

class RecipesCRUD(private val recipesRepository: RecipesRepository) {

    fun getRecipesFromRemote() = recipesRepository.getRecipesFromLocal()

    fun insertRecipesItem(recipesResponseItem: RecipesResponseItem) {
        recipesRepository.insertRecipesItem(recipesResponseItem)
    }

}