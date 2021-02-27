package com.example.myrecipes.domain.usecase

import com.example.myrecipes.data.db.model.Recipe
import com.example.myrecipes.domain.repository.RecipesRepository

class GetSnackRecipesUseCase(private val recipesRepository: RecipesRepository) {

    fun execute() = recipesRepository.getSnackRecipes()

}