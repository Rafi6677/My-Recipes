package com.example.myrecipes.data.repository

import com.example.myrecipes.data.db.model.Recipe
import com.example.myrecipes.data.repository.datasource.RecipesDataSource
import com.example.myrecipes.domain.repository.RecipesRepository

class RecipesRepositoryImpl(private val dataSource: RecipesDataSource) : RecipesRepository {

    override suspend fun addRecipe(recipe: Recipe) {
        dataSource.insertRecipeIntoDB(recipe)
    }

    override suspend fun deleteRecipe(recipe: Recipe) {
        dataSource.deleteRecipeFromDB(recipe)
    }

    override suspend fun editRecipe(recipe: Recipe) {
        dataSource.updateRecipeToDB(recipe)
    }

    override suspend fun getFavouriteRecipes(): List<Recipe> = dataSource.getFavouriteRecipesFromDB()

    override suspend fun getSearchedFavouriteRecipes(
        searchedQuery: String
    ): List<Recipe> = dataSource.getSearchedFavouriteRecipesFromDB(searchedQuery)

    override suspend fun getRecipesByCategoryId(
        categoryId: Int
    ): List<Recipe> = dataSource.getRecipesByCategoryIdFromDB(categoryId)

    override suspend fun getSearchedRecipesByCategoryId(
        categoryId: Int,
        searchedQuery: String
    ): List<Recipe> = dataSource.getSearchedRecipesByCategoryIdFromDB(
        categoryId,
        searchedQuery
    )

}