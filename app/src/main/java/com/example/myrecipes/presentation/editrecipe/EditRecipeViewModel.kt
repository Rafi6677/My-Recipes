package com.example.myrecipes.presentation.editrecipe

import androidx.lifecycle.ViewModel
import com.example.myrecipes.domain.usecase.AddRecipeUseCase
import com.example.myrecipes.domain.usecase.EditRecipeUseCase

class EditRecipeViewModel(
    private val addRecipeUseCase: AddRecipeUseCase,
    private val editRecipeUseCase: EditRecipeUseCase
) : ViewModel() {



}