package com.etisalat.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.etisalat.domain.model.RecipesResponse
import com.etisalat.domain.model.RecipesResponseItem
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object (DAO) interface for recipes.
 */
@Dao
interface RecipesDao {

    /**
     * Inserts a [RecipesResponse] object into the database. If there is a conflict,
     * it replaces the existing data.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRecipesResponse(recipesResponse: RecipesResponse)

    /**
     * Retrieves all recipes from the database, ordered by name in ascending order,
     * as a [Flow] of [List] of [RecipesResponseItem].
     */
    @Query("SELECT * FROM recipes ORDER BY name ASC")
    fun getRecipes(): Flow<List<RecipesResponseItem>>

}