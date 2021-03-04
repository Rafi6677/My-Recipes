package com.example.myrecipes.data.repository.datasource

import androidx.lifecycle.LiveData
import com.example.myrecipes.data.db.model.Recipe

interface RecipesDataSource {

    suspend fun insertRecipeIntoDB(recipe: Recipe)
    suspend fun deleteRecipeFromDB(recipe: Recipe)
    suspend fun updateRecipeToDB(recipe: Recipe)
    suspend  fun getFavouriteRecipesFromDB(): List<Recipe>
    suspend fun getBreakfastRecipesFromDB(): List<Recipe>
    suspend  fun getSoupRecipesFromDB(): List<Recipe>
    suspend  fun getMainDishRecipesFromDB(): List<Recipe>
    suspend   fun getSaladRecipesFromDB(): List<Recipe>
    suspend  fun getSnackRecipesFromDB(): List<Recipe>
    suspend  fun getDessertRecipesFromDB(): List<Recipe>
    suspend  fun getDrinkRecipesFromDB(): List<Recipe>

}