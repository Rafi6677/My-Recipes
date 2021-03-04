package com.example.myrecipes.data.repository

import androidx.lifecycle.LiveData
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

    override suspend fun getBreakfastRecipes(): List<Recipe> = dataSource.getBreakfastRecipesFromDB()

    override suspend fun getSoupRecipes(): List<Recipe> = dataSource.getSoupRecipesFromDB()

    override suspend fun getMainDishRecipes(): List<Recipe> = dataSource.getMainDishRecipesFromDB()

    override suspend fun getSaladRecipes(): List<Recipe> = dataSource.getSaladRecipesFromDB()

    override suspend fun getSnackRecipes(): List<Recipe> = dataSource.getSnackRecipesFromDB()

    override suspend fun getDessertRecipes(): List<Recipe> = dataSource.getDessertRecipesFromDB()

    override suspend fun getDrinkRecipes(): List<Recipe> = dataSource.getDrinkRecipesFromDB()

}