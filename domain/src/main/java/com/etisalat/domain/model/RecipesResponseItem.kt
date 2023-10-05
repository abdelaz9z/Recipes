package com.etisalat.domain.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

/**
 * Parcelable data class representing a recipe response item.
 *
 * @param calories The calorie information of the recipe.
 * @param carbos The carbohydrate information of the recipe.
 * @param country The country associated with the recipe. Default value is "Unknown".
 * @param description The description of the recipe.
 * @param difficulty The difficulty level of the recipe.
 * @param fats The fat information of the recipe.
 * @param headline The headline of the recipe.
 * @param id The unique identifier of the recipe. This field is used as the primary key in the database.
 * @param image The URL of the recipe image.
 * @param name The name of the recipe.
 * @param proteins The protein information of the recipe.
 * @param thumb The thumbnail URL of the recipe image.
 * @param time The time required to prepare the recipe.
 */
@Parcelize
@Entity(tableName = "recipes")
data class RecipesResponseItem(
    val calories: String,
    val carbos: String,
    @ColumnInfo(defaultValue = "Unknown")
    val country: String,
    val description: String,
    val difficulty: Int,
    val fats: String,
    val headline: String,
    @PrimaryKey
    val id: String,
    val image: String,
    val name: String,
    val proteins: String,
    val thumb: String,
    val time: String
) : Parcelable {

    /**
     * Generates the hash code for the recipe response item.
     *
     * @return The generated hash code.
     */
    override fun hashCode(): Int {
        var result = id.hashCode()
        if (thumb.isNullOrEmpty()) {
            result = 31 * result + thumb.hashCode()
        }
        return result
    }
}