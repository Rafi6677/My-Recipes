package com.example.myrecipes.di

import com.example.myrecipes.data.db.dao.RecipesDAO
import com.example.myrecipes.data.repository.datasource.RecipesDataSource
import com.example.myrecipes.data.repository.datasourceimpl.RecipesDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Singleton
    @Provides
    fun provideRecipesDataSource(
        dao: RecipesDAO
    ): RecipesDataSource {
        return RecipesDataSourceImpl(dao)
    }

}