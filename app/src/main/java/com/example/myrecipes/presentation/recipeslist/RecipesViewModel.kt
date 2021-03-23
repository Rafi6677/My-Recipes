package com.example.myrecipes.presentation.recipeslist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myrecipes.data.db.model.Category
import com.example.myrecipes.data.db.model.Recipe
import com.example.myrecipes.domain.usecase.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(
    private val getFavouriteRecipesUseCase: GetFavouriteRecipesUseCase,
    private val getBreakfastRecipesUseCase: GetBreakfastRecipesUseCase,
    private val getSoupRecipesUseCase: GetSoupRecipesUseCase,
    private val getMainDishRecipesUseCase: GetMainDishRecipesUseCase,
    private val getSaladRecipesUseCase: GetSaladRecipesUseCase,
    private val getSnackRecipesUseCase: GetSnackRecipesUseCase,
    private val getDessertRecipesUseCase: GetDessertRecipesUseCase,
    private val getDrinkRecipesUseCase: GetDrinkRecipesUseCase
) : ViewModel() {

    val recipesList = MutableLiveData<List<Recipe>>()

    fun getRecipesByCategoryId(categoryId: Int) {
        viewModelScope.launch {
            recipesList.value = when (categoryId) {
                -1 -> getFavouriteRecipesUseCase.execute()
                Category.Breakfast.categoryId -> getBreakfastRecipesUseCase.execute()
                Category.Soup.categoryId -> getSoupRecipesUseCase.execute()
                Category.MainDish.categoryId -> getMainDishRecipesUseCase.execute()
                Category.Salad.categoryId -> getSaladRecipesUseCase.execute()
                Category.Snack.categoryId -> getSnackRecipesUseCase.execute()
                Category.Dessert.categoryId -> getDessertRecipesUseCase.execute()
                Category.Drinks.categoryId -> getDrinkRecipesUseCase.execute()
                else -> ArrayList()
            }
        }
    }

}