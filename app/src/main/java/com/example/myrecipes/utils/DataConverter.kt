package com.example.myrecipes.utils

import androidx.room.TypeConverter
import com.example.myrecipes.data.db.model.Ingredient
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DataConverter {

    @TypeConverter
    fun convertIngredientsListToJSON(ingredientsList: List<Ingredient>): String {
        val type = object : TypeToken<List<Ingredient>>() {}.type

        return Gson().toJson(ingredientsList, type)
    }

    @TypeConverter
    fun convertJSONToProductsList(ingredientsJSON: String): List<Ingredient> {
        val type = object : TypeToken<List<Ingredient>>() {}.type

        return Gson().fromJson(ingredientsJSON, type)
    }

}