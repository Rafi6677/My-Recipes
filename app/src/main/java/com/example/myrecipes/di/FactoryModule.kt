package com.example.myrecipes.di

import com.example.myrecipes.domain.usecase.AddRecipeUseCase
import com.example.myrecipes.domain.usecase.EditRecipeUseCase
import com.example.myrecipes.presentation.editrecipe.EditRecipeViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {

    @Singleton
    @Provides
    fun provideEditRecipeViewModelFactory(
        addRecipeUseCase: AddRecipeUseCase,
        editRecipeUseCase: EditRecipeUseCase
    ): EditRecipeViewModelFactory {
        return EditRecipeViewModelFactory(
            addRecipeUseCase,
            editRecipeUseCase
        )
    }

}