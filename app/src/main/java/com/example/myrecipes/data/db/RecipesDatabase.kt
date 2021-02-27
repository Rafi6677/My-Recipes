package com.example.myrecipes.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myrecipes.data.db.dao.RecipesDAO
import com.example.myrecipes.data.db.model.Recipe
import com.example.myrecipes.utils.DataConverter

@Database(
    entities = [Recipe::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(DataConverter::class)
abstract class RecipesDatabase : RoomDatabase() {

    abstract fun RecipesDAO(): RecipesDAO

}