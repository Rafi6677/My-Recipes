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

    override fun getFavouriteRecipes(): LiveData<List<Recipe>> = dataSource.getFavouriteRecipesFromDB()

    override fun getBreakfastRecipes(): LiveData<List<Recipe>> = dataSource.getBreakfastRecipesFromDB()

    override fun getSoupRecipes(): LiveData<List<Recipe>> = dataSource.getSoupRecipesFromDB()

    override fun getMainDishRecipes(): LiveData<List<Recipe>> = dataSource.getMainDishRecipesFromDB()

    override fun getSaladRecipes(): LiveData<List<Recipe>> = dataSource.getSaladRecipesFromDB()

    override fun getSnackRecipes(): LiveData<List<Recipe>> = dataSource.getSnackRecipesFromDB()

    override fun getDessertRecipes(): LiveData<List<Recipe>> = dataSource.getDessertRecipesFromDB()

    override fun getDrinkRecipes(): LiveData<List<Recipe>> = dataSource.getDrinkRecipesFromDB()

}