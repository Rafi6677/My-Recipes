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

    override fun getFavouriteRecipesFromDB(): LiveData<List<Recipe>> = dao.getFavouriteRecipes()

    override fun getBreakfastRecipesFromDB(): LiveData<List<Recipe>> = dao.getBreakfastRecipes()

    override fun getSoupRecipesFromDB(): LiveData<List<Recipe>> = dao.getSoupRecipes()

    override fun getMainDishRecipesFromDB(): LiveData<List<Recipe>> = dao.getMainDishRecipes()

    override fun getSaladRecipesFromDB(): LiveData<List<Recipe>> = dao.getSaladRecipes()

    override fun getSnackRecipesFromDB(): LiveData<List<Recipe>> = dao.getSnackRecipes()

    override fun getDessertRecipesFromDB(): LiveData<List<Recipe>> = dao.getDessertRecipes()

    override fun getDrinkRecipesFromDB(): LiveData<List<Recipe>> = dao.getDrinkRecipes()

}