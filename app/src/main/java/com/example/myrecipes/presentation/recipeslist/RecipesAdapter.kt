package com.example.myrecipes.presentation.recipeslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myrecipes.data.db.model.Recipe
import com.example.myrecipes.databinding.ItemRecipeBinding

class RecipesAdapter(
    private val clickListener: (Recipe) -> Unit
): RecyclerView.Adapter<RecipesAdapter.RecipesViewHolder>() {

    private val recipesList = ArrayList<Recipe>()

    fun setRecipesList(recipes: List<Recipe>?) {
        recipesList.clear()

        if (recipes != null) {
            recipesList.addAll(recipes)
        }
    }

    fun getRecipesList(): ArrayList<Recipe> {
        return recipesList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesViewHolder {
        val binding = ItemRecipeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return RecipesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipesViewHolder, position: Int) {
        val recipe = recipesList[position]
        holder.bind(recipe)
    }

    override fun getItemCount(): Int {
        return recipesList.size
    }

    inner class RecipesViewHolder(
        private val binding: ItemRecipeBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(recipe: Recipe) {
            binding.recipeTitleTextView.text = recipe.title
            binding.recipeCardView.setOnClickListener { clickListener(recipe) }
        }

    }

}