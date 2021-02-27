package com.example.myrecipes.di

import android.content.Context
import androidx.room.Room
import com.example.myrecipes.data.db.RecipesDatabase
import com.example.myrecipes.data.db.dao.RecipesDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): RecipesDatabase {
        return Room.databaseBuilder(
            context,
            RecipesDatabase::class.java,
            "recipes_database"
        ).build()
    }

    @Singleton
    @Provides
    fun provideRecipesDAO(recipesDatabase: RecipesDatabase): RecipesDAO {
        return recipesDatabase.RecipesDAO()
    }

}