package com.example.myrecipes.domain.usecase

import com.example.myrecipes.domain.repository.RecipesRepository

class GetFavouriteRecipesUseCase(private val recipesRepository: RecipesRepository) {

    suspend fun execute() = recipesRepository.getFavouriteRecipes()

}