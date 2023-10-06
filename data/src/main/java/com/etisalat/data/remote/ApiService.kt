package com.etisalat.data.remote

import com.etisalat.domain.model.RecipesResponse
import retrofit2.http.GET

/**
 * Interface for API service.
 */
interface ApiService {

    /**
     * Retrieves recipes from the remote API.
     *
     * @return [RecipesResponse] object containing the recipes.
     */
    @GET("recipes.json")
    suspend fun getRecipes(): RecipesResponse

}