package com.example.myrecipes.presentation.editrecipe

enum class RecipeOperationType(val value: Int) {

    Add(0),
    Edit(1),
    Display(2);

    companion object {
        private val map = values().associateBy(RecipeOperationType::value)

        fun setByValue(value: Int) = map[value]
    }

}