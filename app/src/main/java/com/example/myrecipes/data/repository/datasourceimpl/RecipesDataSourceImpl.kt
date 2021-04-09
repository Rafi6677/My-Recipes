package com.example.myrecipes.data.repository.datasourceimpl

import com.example.myrecipes.data.db.dao.RecipesDAO
import com.example.myrecipes.data.db.model.Recipe
import com.example.myrecipes.data.repository.datasource.RecipesDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecipesDataSourceImpl(private val dao: RecipesDAO) : RecipesDataSource {

    override suspend fun insertRecipeIntoDB(recipe: Recipe) {
        CoroutineScope(Dispatchers.IO).launch {
            dao.insertRecipe(recipe)
        }
    }

    override suspend fun deleteRecipeFromDB(recipe: Recipe) {
        CoroutineScope(Dispatchers.IO).launch {
            dao.deleteRecipe(recipe)
        }
    }

    override suspend fun updateRecipeToDB(recipe: Recipe) {
        CoroutineScope(Dispatchers.IO).launch {
            dao.updateRecipe(recipe)
        }
    }

    override suspend fun getFavouriteRecipesFromDB(): List<Recipe> = dao
        .getFavouriteRecipes()

    override suspend fun getSearchedFavouriteRecipesFromDB(
        searchQuery: String
    ): List<Recipe> = dao.getSearchedFavouriteRecipes(searchQuery)

    override suspend fun getRecipesByCategoryIdFromDB(
        categoryId: Int
    ): List<Recipe> = dao.getRecipesByCategoryId(categoryId)

    override suspend fun getSearchedRecipesByCategoryIdFromDB(
        categoryId: Int,
        searchQuery: String
    ): List<Recipe> = dao.getSearchedRecipesByCategoryId(
        categoryId,
        searchQuery
    )

}