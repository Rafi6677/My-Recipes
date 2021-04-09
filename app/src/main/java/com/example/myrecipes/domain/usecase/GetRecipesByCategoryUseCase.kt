package com.example.myrecipes.domain.usecase

import com.example.myrecipes.domain.repository.RecipesRepository

class GetRecipesByCategoryUseCase(private val recipesRepository: RecipesRepository) {

    suspend fun execute(
        categoryId: Int
    ) = recipesRepository.getRecipesByCategoryId(categoryId)

}