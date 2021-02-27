package com.example.myrecipes.domain.usecase

import com.example.myrecipes.data.db.model.Recipe
import com.example.myrecipes.domain.repository.RecipesRepository

class AddRecipeUseCase(private val recipesRepository: RecipesRepository) {

    suspend fun execute(recipe: Recipe) = recipesRepository.addRecipe(recipe)

}