package com.example.myrecipes.presentation.editrecipe

import android.content.res.Resources
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myrecipes.R
import com.example.myrecipes.data.db.model.Category
import com.example.myrecipes.data.db.model.Ingredient
import com.example.myrecipes.data.db.model.Recipe
import com.example.myrecipes.domain.usecase.AddRecipeUseCase
import com.example.myrecipes.domain.usecase.EditRecipeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

@HiltViewModel
class EditRecipeViewModel @Inject constructor(
    private val addRecipeUseCase: AddRecipeUseCase,
    private val editRecipeUseCase: EditRecipeUseCase
) : ViewModel() {

    var ingredientsList: ArrayList<Ingredient> = ArrayList()
    var preparationDescription: String = ""
    var categoryId: Int = 0

    fun mapDialogInterfaceCategoryToCategoryId(dialogInterfaceId: Int): Int {
        return when (dialogInterfaceId) {
            R.id.category1_radioButton -> 1
            R.id.category2_radioButton -> 2
            R.id.category3_radioButton -> 3
            R.id.category4_radioButton -> 4
            R.id.category5_radioButton -> 5
            R.id.category6_radioButton -> 6
            R.id.category7_radioButton -> 7
            else -> 1
        }
    }

    fun getCategoryName(category: Category, resources: Resources): String {
        return when (category) {
            Category.Breakfast -> resources.getString(R.string.breakfast)
            Category.Soup -> resources.getString(R.string.soup)
            Category.MainDish -> resources.getString(R.string.main_dish)
            Category.Salad -> resources.getString(R.string.salad)
            Category.Snack -> resources.getString(R.string.snack)
            Category.Dessert -> resources.getString(R.string.dessert)
            Category.Drinks -> resources.getString(R.string.drink)
        }
    }

    fun saveRecipe(title: String) {
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

}