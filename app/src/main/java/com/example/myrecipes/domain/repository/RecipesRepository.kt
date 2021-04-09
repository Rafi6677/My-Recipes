package com.example.myrecipes.domain.repository

import com.example.myrecipes.data.db.model.Recipe

interface RecipesRepository {

    suspend fun addRecipe(recipe: Recipe)
    suspend fun deleteRecipe(recipe: Recipe)
    suspend fun editRecipe(recipe: Recipe)
    suspend fun getFavouriteRecipes(): List<Recipe>?
    suspend fun getSearchedFavouriteRecipes(searchedQuery: String): List<Recipe>?
    suspend fun getRecipesByCategoryId(categoryId: Int): List<Recipe>?
    suspend fun getSearchedRecipesByCategoryId(categoryId: Int, searchedQuery: String): List<Recipe>?

}