package com.example.myrecipes.domain.repository

import androidx.lifecycle.LiveData
import com.example.myrecipes.data.db.model.Recipe

interface RecipesRepository {

    suspend fun addRecipe(recipe: Recipe)
    suspend fun deleteRecipe(recipe: Recipe)
    suspend fun editRecipe(recipe: Recipe)
    fun getBreakfastRecipes(): LiveData<List<Recipe>>
    fun getSoupRecipes(): LiveData<List<Recipe>>
    fun getMainDishRecipes(): LiveData<List<Recipe>>
    fun getDinnerRecipes(): LiveData<List<Recipe>>
    fun getSnackRecipes(): LiveData<List<Recipe>>
    fun getDessertRecipes(): LiveData<List<Recipe>>
    fun getDrinkRecipes(): LiveData<List<Recipe>>

}