package com.example.myrecipes.data.db.model

enum class Category(val categoryId: Int) {

    Breakfast(1),
    Soup(2),
    MainDish(3),
    Salad(4),
    Snack(5),
    Dessert(6),
    Drinks(7);

    companion object {
        private val map = values().associateBy(Category::categoryId)

        fun setByCategoryId(categoryId: Int) = map[categoryId]!!
    }

}