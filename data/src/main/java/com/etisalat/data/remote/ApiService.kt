package com.etisalat.data.remote

import com.etisalat.domain.model.RecipesResponse
import retrofit2.http.GET

interface ApiService {
    @GET("recipes.json")
    suspend fun getRecipes(): RecipesResponse
}