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
    fun provideGetBreakfastRecipesUseCase(recipesRepository: RecipesRepository): GetBreakfastRecipesUseCase {
        return GetBreakfastRecipesUseCase(recipesRepository)
    }

    @Singleton
    @Provides
    fun provideGetSoupRecipesUseCase(recipesRepository: RecipesRepository): GetSoupRecipesUseCase {
        return GetSoupRecipesUseCase(recipesRepository)
    }

    @Singleton
    @Provides
    fun provideGetMainDishRecipesUseCase(recipesRepository: RecipesRepository): GetMainDishRecipesUseCase {
        return GetMainDishRecipesUseCase(recipesRepository)
    }

    @Singleton
    @Provides
    fun provideGetDinnerRecipesUseCase(recipesRepository: RecipesRepository): GetSaladRecipesUseCase {
        return GetSaladRecipesUseCase(recipesRepository)
    }

    @Singleton
    @Provides
    fun provideGetSnackRecipesUseCase(recipesRepository: RecipesRepository): GetSnackRecipesUseCase {
        return GetSnackRecipesUseCase(recipesRepository)
    }

    @Singleton
    @Provides
    fun provideGetDessertRecipesUseCase(recipesRepository: RecipesRepository): GetDessertRecipesUseCase {
        return GetDessertRecipesUseCase(recipesRepository)
    }

    @Singleton
    @Provides
    fun provideGetDrinkRecipesUseCase(recipesRepository: RecipesRepository): GetDrinkRecipesUseCase {
        return GetDrinkRecipesUseCase(recipesRepository)
    }

}