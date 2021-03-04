package com.example.myrecipes.domain.repository

import androidx.lifecycle.LiveData
import com.example.myrecipes.data.db.model.Recipe

interface RecipesRepository {

    suspend fun addRecipe(recipe: Recipe)
    suspend fun deleteRecipe(recipe: Recipe)
    suspend fun editRecipe(recipe: Recipe)
    suspend fun getFavouriteRecipes(): List<Recipe>?
    suspend fun getBreakfastRecipes(): List<Recipe>?
    suspend fun getSoupRecipes(): List<Recipe>?
    suspend fun getMainDishRecipes(): List<Recipe>?
    suspend fun getSaladRecipes(): List<Recipe>?
    suspend fun getSnackRecipes(): List<Recipe>?
    suspend fun getDessertRecipes(): List<Recipe>?
    suspend fun getDrinkRecipes(): List<Recipe>?

}