package com.example.myrecipes.data.repository.datasource

import androidx.lifecycle.LiveData
import com.example.myrecipes.data.db.model.Recipe

interface RecipesDataSource {

    suspend fun insertRecipeIntoDB(recipe: Recipe)
    suspend fun deleteRecipeFromDB(recipe: Recipe)
    suspend fun updateRecipeToDB(recipe: Recipe)
    fun getFavouriteRecipesFromDB(): LiveData<List<Recipe>>
    fun getBreakfastRecipesFromDB(): LiveData<List<Recipe>>
    fun getSoupRecipesFromDB(): LiveData<List<Recipe>>
    fun getMainDishRecipesFromDB(): LiveData<List<Recipe>>
    fun getSaladRecipesFromDB(): LiveData<List<Recipe>>
    fun getSnackRecipesFromDB(): LiveData<List<Recipe>>
    fun getDessertRecipesFromDB(): LiveData<List<Recipe>>
    fun getDrinkRecipesFromDB(): LiveData<List<Recipe>>

}