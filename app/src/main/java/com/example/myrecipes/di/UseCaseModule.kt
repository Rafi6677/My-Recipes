package com.example.myrecipes.di

import com.example.myrecipes.domain.repository.RecipesRepository
import com.example.myrecipes.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Singleton
    @Provides
    fun provideAddRecipeUseCase(recipesRepository: RecipesRepository): AddRecipeUseCase {
        return AddRecipeUseCase(recipesRepository)
    }

    @Singleton
    @Provides
    fun provideDeleteRecipeUseCase(recipesRepository: RecipesRepository): DeleteRecipeUseCase {
        return DeleteRecipeUseCase(recipesRepository)
    }

    @Singleton
    @Provides
    fun provideEditRecipeUseCase(recipesRepository: RecipesRepository): EditRecipeUseCase {
        return EditRecipeUseCase(recipesRepository)
    }

    @Singleton
    @Provides
    fun provideGetFavouriteRecipesUseCase(recipesRepository: RecipesRepository): GetFavouriteRecipesUseCase {
        return GetFavouriteRecipesUseCase(recipesRepository)
    }

    @Singleton
    @Provides
    fun provideGetSearchedFavouriteRecipesUseCase(recipesRepository: RecipesRepository): GetSearchedFavoriteRecipesUseCase {
        return GetSearchedFavoriteRecipesUseCase(recipesRepository)
    }

    @Singleton
    @Provides
    fun provideGetRecipesByCategoryUseCase(recipesRepository: RecipesRepository): GetRecipesByCategoryUseCase {
        return GetRecipesByCategoryUseCase(recipesRepository)
    }

    @Singleton
    @Provides
    fun provideGetSearchedRecipesByCategoryUseCase(recipesRepository: RecipesRepository): GetSearchedRecipesByCategoryUseCase {
        return GetSearchedRecipesByCategoryUseCase(recipesRepository)
    }

}