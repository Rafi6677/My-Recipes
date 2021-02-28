package com.example.myrecipes.domain.usecase

import com.example.myrecipes.domain.repository.RecipesRepository

class GetSaladRecipesUseCase(private val recipesRepository: RecipesRepository) {

    fun execute() = recipesRepository.getSaladRecipes()

}