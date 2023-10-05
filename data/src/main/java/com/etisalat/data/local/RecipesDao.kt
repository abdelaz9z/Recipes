package com.etisalat.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.etisalat.domain.model.RecipesResponse
import com.etisalat.domain.model.RecipesResponseItem
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertRecipes(recipesResponseItem: RecipesResponseItem)

    @Query("SELECT * FROM recipes ORDER BY name ASC")
    fun getRecipes(): Flow<List<RecipesResponseItem>>

}