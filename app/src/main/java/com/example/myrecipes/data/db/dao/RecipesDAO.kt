package com.example.myrecipes.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.myrecipes.data.db.model.Recipe

@Dao
interface RecipesDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipe(recipe: Recipe): Long

    @Delete
    suspend fun deleteRecipe(recipe: Recipe): Long

    @Update
    suspend fun updateRecipe(recipe: Recipe): Long

    @Query("SELECT * FROM recipes WHERE is_favourite = 1 ORDER BY name")
    fun getFavouriteRecipes(): LiveData<List<Recipe>>

    @Query("SELECT * FROM recipes WHERE category_id = 0 ORDER BY name")
    fun getBreakfastRecipes(): LiveData<List<Recipe>>

    @Query("SELECT * FROM recipes WHERE category_id = 1 ORDER BY name")
    fun getSoupRecipes(): LiveData<List<Recipe>>

    @Query("SELECT * FROM recipes WHERE category_id = 2 ORDER BY name")
    fun getMainDishRecipes(): LiveData<List<Recipe>>

    @Query("SELECT * FROM recipes WHERE category_id = 3 ORDER BY name")
    fun getDinnerRecipes(): LiveData<List<Recipe>>

    @Query("SELECT * FROM recipes WHERE category_id = 4 ORDER BY name")
    fun getSnackRecipes(): LiveData<List<Recipe>>

    @Query("SELECT * FROM recipes WHERE category_id = 5 ORDER BY name")
    fun getDessertRecipes(): LiveData<List<Recipe>>

    @Query("SELECT * FROM recipes WHERE category_id = 6 ORDER BY name")
    fun getDrinkRecipes(): LiveData<List<Recipe>>

}