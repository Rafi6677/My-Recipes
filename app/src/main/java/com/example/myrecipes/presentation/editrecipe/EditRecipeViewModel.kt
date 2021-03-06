package com.example.myrecipes.presentation.editrecipe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myrecipes.data.db.model.Ingredient
import com.example.myrecipes.data.db.model.Recipe
import com.example.myrecipes.domain.usecase.AddRecipeUseCase
import com.example.myrecipes.domain.usecase.DeleteRecipeUseCase
import com.example.myrecipes.domain.usecase.EditRecipeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

@HiltViewModel
class EditRecipeViewModel @Inject constructor(
    private val addRecipeUseCase: AddRecipeUseCase,
    private val editRecipeUseCase: EditRecipeUseCase,
    private val deleteRecipeUseCase: DeleteRecipeUseCase
) : ViewModel() {

    var title: String = ""
    var ingredientsList: ArrayList<Ingredient> = ArrayList()
    var preparationDescription: String = ""
    var categoryId: Int = 0
    var isFavorite: Boolean = false

    fun saveRecipe() {
        val recipe = Recipe(
            0,
            title,
            ingredientsList,
            preparationDescription,
            "",
            categoryId,
            false,
            Date().time
        )

        viewModelScope.launch {
            addRecipeUseCase.execute(recipe)
        }
    }

    fun updateRecipe(recipe: Recipe) {
        val updatedRecipe = Recipe(
            recipe.id,
            title,
            ingredientsList,
            preparationDescription,
            recipe.notes,
            categoryId,
            isFavorite,
            Date().time
        )

        viewModelScope.launch {
            editRecipeUseCase.execute(updatedRecipe)
        }
    }

    fun deleteRecipe(recipe: Recipe) {
        viewModelScope.launch {
            deleteRecipeUseCase.execute(recipe)
        }
    }

    fun changeRecipeIsFavorite(recipe: Recipe): Recipe {
        val updatedRecipe = Recipe(
            recipe.id,
            recipe.title,
            recipe.ingredients,
            recipe.preparationDescription,
            recipe.notes,
            recipe.categoryId,
            !recipe.isFavourite,
            Date().time
        )

        viewModelScope.launch {
            editRecipeUseCase.execute(updatedRecipe)
        }

        return updatedRecipe
    }

}