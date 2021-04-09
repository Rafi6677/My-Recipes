package com.example.myrecipes.domain.usecase

import com.example.myrecipes.domain.repository.RecipesRepository

class GetSearchedRecipesByCategoryUseCase(private val recipesRepository: RecipesRepository) {

    suspend fun execute(
        categoryId: Int,
        searchQuery: String
    ) = recipesRepository.getSearchedRecipesByCategoryId(categoryId, searchQuery)

}