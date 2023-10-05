package com.etisalat.domain.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

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
    override fun hashCode(): Int {
        var result = id.hashCode()
        if (thumb.isNullOrEmpty()) {
            result = 31 * result + thumb.hashCode()
        }
        return result
    }
}