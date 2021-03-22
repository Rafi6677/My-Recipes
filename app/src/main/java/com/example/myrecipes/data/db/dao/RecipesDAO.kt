package com.example.myrecipes.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.myrecipes.data.db.model.Recipe

@Dao
interface RecipesDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipe(recipe: Recipe): Long

    @Delete
    suspend fun deleteRecipe(recipe: Recipe): Int

    @Update
    suspend fun updateRecipe(recipe: Recipe): Int

    @Query("SELECT * FROM recipes WHERE is_favourite = 1 ORDER BY name")
    suspend fun getFavouriteRecipes(): List<Recipe>

    @Query("SELECT * FROM recipes WHERE category_id = 1 ORDER BY name")
    suspend fun getBreakfastRecipes(): List<Recipe>

    @Query("SELECT * FROM recipes WHERE category_id = 2 ORDER BY name")
    suspend fun getSoupRecipes(): List<Recipe>

    @Query("SELECT * FROM recipes WHERE category_id = 3 ORDER BY name")
    suspend fun getMainDishRecipes(): List<Recipe>

    @Query("SELECT * FROM recipes WHERE category_id = 4 ORDER BY name")
    suspend fun getSaladRecipes(): List<Recipe>

    @Query("SELECT * FROM recipes WHERE category_id = 5 ORDER BY name")
    suspend fun getSnackRecipes(): List<Recipe>

    @Query("SELECT * FROM recipes WHERE category_id = 6 ORDER BY name")
    suspend fun getDessertRecipes(): List<Recipe>

    @Query("SELECT * FROM recipes WHERE category_id = 7 ORDER BY name")
    suspend fun getDrinkRecipes(): List<Recipe>

}