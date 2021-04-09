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
    private val getSearchedFavouriteRecipesUseCase: GetSearchedFavoriteRecipesUseCase,
    private val getRecipesByCategoryUseCase: GetRecipesByCategoryUseCase,
    private val getSearchedRecipesByCategoryUseCase: GetSearchedRecipesByCategoryUseCase
) : ViewModel() {

    val recipesList = MutableLiveData<List<Recipe>>()

    fun getRecipesByCategoryId(
        categoryId: Int
    ) = viewModelScope.launch {
        if (categoryId == -1) {
            recipesList.value = getFavouriteRecipesUseCase.execute()
        } else {
            recipesList.value = getRecipesByCategoryUseCase.execute(categoryId)
        }
    }

    val searchedRecipesList = MutableLiveData<List<Recipe>>()

    fun searchRecipes(
        categoryId: Int,
        searchQuery: String
    ) = viewModelScope.launch {
        if (categoryId == -1) {
            searchedRecipesList.value = getSearchedFavouriteRecipesUseCase.execute(searchQuery)
        } else {
            searchedRecipesList.value = getSearchedRecipesByCategoryUseCase.execute(categoryId, searchQuery)
        }
    }

}