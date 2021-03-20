package com.example.myrecipes.presentation.editrecipe

import androidx.lifecycle.ViewModel
import com.example.myrecipes.domain.usecase.AddRecipeUseCase
import com.example.myrecipes.domain.usecase.EditRecipeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EditRecipeViewModel @Inject constructor(
    private val addRecipeUseCase: AddRecipeUseCase,
    private val editRecipeUseCase: EditRecipeUseCase
) : ViewModel() {



}