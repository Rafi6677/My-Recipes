package com.example.myrecipes.domain.usecase

import com.example.myrecipes.domain.repository.RecipesRepository

class GetFavouriteRecipes(private val recipesRepository: RecipesRepository) {

    fun execute() = recipesRepository.getFavouriteRecipes()

}