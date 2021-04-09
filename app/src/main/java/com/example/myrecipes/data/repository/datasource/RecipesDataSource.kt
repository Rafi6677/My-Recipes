package com.example.myrecipes.data.repository.datasource

import com.example.myrecipes.data.db.model.Recipe

interface RecipesDataSource {

    suspend fun insertRecipeIntoDB(recipe: Recipe)
    suspend fun deleteRecipeFromDB(recipe: Recipe)
    suspend fun updateRecipeToDB(recipe: Recipe)
    suspend fun getFavouriteRecipesFromDB(): List<Recipe>
    suspend fun getSearchedFavouriteRecipesFromDB(searchQuery: String): List<Recipe>
    suspend fun getRecipesByCategoryIdFromDB(categoryId: Int): List<Recipe>
    suspend fun getSearchedRecipesByCategoryIdFromDB(categoryId: Int, searchQuery: String): List<Recipe>

}