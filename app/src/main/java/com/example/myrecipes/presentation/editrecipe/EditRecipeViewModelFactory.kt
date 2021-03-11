package com.example.myrecipes.presentation.editrecipe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myrecipes.domain.usecase.AddRecipeUseCase
import com.example.myrecipes.domain.usecase.EditRecipeUseCase
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class EditRecipeViewModelFactory(
    private val addRecipeUseCase: AddRecipeUseCase,
    private val editRecipeUseCase: EditRecipeUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EditRecipeViewModel::class.java)) {
            return EditRecipeViewModel(
                addRecipeUseCase,
                editRecipeUseCase
            ) as T
        }

        throw IllegalArgumentException("Unknown View Model Class")
    }

}