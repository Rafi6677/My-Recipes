package com.example.myrecipes.domain.usecase

import com.example.myrecipes.domain.repository.RecipesRepository

class GetSearchedFavoriteRecipesUseCase(private val recipesRepository: RecipesRepository) {

    suspend fun execute(searchedQuery: String) = recipesRepository.getSearchedFavouriteRecipes(searchedQuery)

}