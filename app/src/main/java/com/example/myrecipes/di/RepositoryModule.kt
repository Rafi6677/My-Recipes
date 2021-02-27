package com.example.myrecipes.di

import com.example.myrecipes.data.repository.RecipesRepositoryImpl
import com.example.myrecipes.data.repository.datasource.RecipesDataSource
import com.example.myrecipes.domain.repository.RecipesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideRecipesRepository(recipesDataSource: RecipesDataSource): RecipesRepository {
        return RecipesRepositoryImpl(recipesDataSource)
    }


}