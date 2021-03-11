package com.example.myrecipes.presentation.editrecipe

enum class RecipeOperationType(val value: Int) {

    Edit(0),
    Display(1);

    companion object {
        private val map = values().associateBy(RecipeOperationType::value)

        fun setByValue(value: Int) = map[value]
    }

}