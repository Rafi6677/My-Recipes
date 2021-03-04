package com.example.myrecipes.data.repository.datasourceimpl

import androidx.lifecycle.LiveData
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

    override suspend fun getFavouriteRecipesFromDB(): List<Recipe> = dao.getFavouriteRecipes()

    override suspend fun getBreakfastRecipesFromDB(): List<Recipe> = dao.getBreakfastRecipes()

    override suspend fun getSoupRecipesFromDB(): List<Recipe> = dao.getSoupRecipes()

    override suspend fun getMainDishRecipesFromDB(): List<Recipe> = dao.getMainDishRecipes()

    override suspend fun getSaladRecipesFromDB(): List<Recipe> = dao.getSaladRecipes()

    override suspend fun getSnackRecipesFromDB(): List<Recipe> = dao.getSnackRecipes()

    override suspend fun getDessertRecipesFromDB(): List<Recipe> = dao.getDessertRecipes()

    override suspend fun getDrinkRecipesFromDB(): List<Recipe> = dao.getDrinkRecipes()

}