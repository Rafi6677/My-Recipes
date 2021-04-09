package com.example.myrecipes.data.db.dao

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

    @Query("SELECT * FROM recipes WHERE is_favourite = 1 ORDER BY title")
    suspend fun getFavouriteRecipes(): List<Recipe>

    @Query("SELECT * FROM recipes WHERE is_favourite = 1 AND title LIKE :searchQuery ORDER BY title")
    suspend fun getSearchedFavouriteRecipes(searchQuery: String): List<Recipe>

    @Query("SELECT * FROM recipes WHERE category_id = :categoryId ORDER BY title")
    suspend fun getRecipesByCategoryId(categoryId: Int): List<Recipe>

    @Query("SELECT * FROM recipes WHERE category_id = :categoryId AND title LIKE :searchQuery ORDER BY title")
    suspend fun getSearchedRecipesByCategoryId(categoryId: Int, searchQuery: String): List<Recipe>

}