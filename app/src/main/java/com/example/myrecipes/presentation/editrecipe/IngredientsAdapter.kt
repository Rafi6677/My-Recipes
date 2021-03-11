package com.example.myrecipes.presentation.editrecipe

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myrecipes.data.db.model.Ingredient
import com.example.myrecipes.databinding.ItemIngredientBinding

class IngredientsAdapter : RecyclerView.Adapter<IngredientsAdapter.IngredientsViewHolder>() {

    private val ingredientsList = ArrayList<Ingredient>()
    private lateinit var recipeOperationType: RecipeOperationType
    private lateinit var editIngredientButtonClickListener: (Ingredient) -> Unit
    private lateinit var deleteIngredientButtonClickListener: (Ingredient) -> Unit

    fun setIngredientsList(ingredients: List<Ingredient>?) {
        ingredientsList.clear()

        if (ingredients != null) {
            ingredientsList.addAll(ingredients)
        }
    }

    fun getIngredientsList(): ArrayList<Ingredient> {
        return ingredientsList
    }

    fun setOperationType(recipeOperationType: RecipeOperationType) {
        this.recipeOperationType = recipeOperationType
    }

    fun setEditIngredientButtonClickListener(clickListener: (Ingredient) -> Unit) {
        editIngredientButtonClickListener = clickListener
    }

    fun setDeleteIngredientButtonClickListener(clickListener: (Ingredient) -> Unit) {
        deleteIngredientButtonClickListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientsViewHolder {
       val binding = ItemIngredientBinding.inflate(
           LayoutInflater.from(parent.context),
           parent,
           false
       )

        return IngredientsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: IngredientsViewHolder, position: Int) {
        val ingredient = ingredientsList[position]
        holder.bind(ingredient)
    }

    override fun getItemCount(): Int {
        return ingredientsList.size
    }

    inner class IngredientsViewHolder(
        private val binding: ItemIngredientBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(ingredient: Ingredient) {
            binding.ingredientNameTextView.text = ingredient.name
            binding.ingredientQuantityTextView.text = ingredient.quantity

            if (recipeOperationType == RecipeOperationType.Edit) {
                binding.editButton.apply {
                    visibility = View.VISIBLE
                    setOnClickListener {
                        editIngredientButtonClickListener
                    }
                }
                binding.deleteButton.apply {
                    visibility = View.VISIBLE
                    setOnClickListener {
                        deleteIngredientButtonClickListener
                    }
                }
            }
        }

    }

}