package com.example.myrecipes.presentation.editrecipe

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myrecipes.R
import com.example.myrecipes.data.db.model.Ingredient
import com.example.myrecipes.data.db.model.Recipe
import com.example.myrecipes.databinding.FragmentIngredientsBinding
import kotlinx.android.synthetic.main.dialog_add_ingredient.view.*
import kotlinx.android.synthetic.main.dialog_recipe_creation.view.*

class IngredientsFragment : Fragment() {

    private lateinit var binding: FragmentIngredientsBinding
    private lateinit var viewModel: EditRecipeViewModel
    private var recipe: Recipe? = null
    lateinit var adapter: IngredientsAdapter

    companion object {
        fun getInstance(): IngredientsFragment {
            return IngredientsFragment()
        }
    }

    private enum class IngredientOperation {
        Add,
        Edit
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_ingredients, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentIngredientsBinding.bind(view)
        viewModel = (activity as EditRecipeActivity).viewModel
        recipe = (activity as EditRecipeActivity).recipe

        initData()
    }

    private fun initData() {
        adapter = IngredientsAdapter()
        adapter.setOperationType((activity as EditRecipeActivity).recipeOperationType)

        when((activity as EditRecipeActivity).recipeOperationType) {
            RecipeOperationType.Add -> {
                binding.addFirstIngredientInfoTextView.visibility = View.VISIBLE
                binding.addIngredientButton.visibility = View.VISIBLE
                binding.addIngredientButton.apply {
                    setOnClickListener {
                        addIngredient()
                    }
                }

                adapter.apply {
                    setEditIngredientButtonClickListener {
                        editIngredient(it)
                    }
                    setDeleteIngredientButtonClickListener {
                        deleteIngredient(it)
                    }
                }
            }
            RecipeOperationType.Edit -> {
                binding.addFirstIngredientInfoTextView.visibility = View.INVISIBLE
                binding.addIngredientButton.visibility = View.VISIBLE
                binding.addIngredientButton.apply {
                    setOnClickListener {
                        addIngredient()
                    }
                }

                adapter.apply {
                    setEditIngredientButtonClickListener {
                        editIngredient(it)
                    }
                    setDeleteIngredientButtonClickListener {
                        deleteIngredient(it)
                    }
                }
            }
            RecipeOperationType.Display -> {
                binding.addFirstIngredientInfoTextView.visibility = View.INVISIBLE
                binding.addIngredientButton.visibility = View.INVISIBLE

                adapter.apply {
                    setIngredientsList(recipe!!.ingredients)
                }
            }
        }

        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.ingredientsRecyclerView.apply {
            adapter = this@IngredientsFragment.adapter
            layoutManager = LinearLayoutManager(this@IngredientsFragment.context)
        }
    }

    private fun addOrEditIngredient(
        ingredientOperation: IngredientOperation,
        ingredient: Ingredient?
    ) {
        val dialogLayout = layoutInflater.inflate(R.layout.dialog_add_ingredient, null)

        AlertDialog.Builder(requireActivity()).apply {
            when (ingredientOperation) {
                IngredientOperation.Add -> {
                    setTitle(R.string.add_ingredient)
                }
                IngredientOperation.Edit -> {
                    setTitle(R.string.edit_ingredient)

                    if (ingredient != null) {
                        dialogLayout.ingredient_name_editText.setText(ingredient.name)
                        dialogLayout.ingredient_quantity_editText.setText(ingredient.quantity)
                    }
                }
            }
            setView(dialogLayout)
            setPositiveButton(R.string.save) { _, _ ->
                trySaveIngredient(
                    ingredientOperation,
                    dialogLayout.ingredient_name_editText.text.toString(),
                    dialogLayout.ingredient_quantity_editText.text.toString(),
                    ingredient
                )
            }
            setNegativeButton(R.string.cancel) { _, _ -> }
            create().show()
        }
    }

    private fun trySaveIngredient(
        ingredientOperation: IngredientOperation,
        name: String,
        quantity: String,
        ingredientToUpdate: Ingredient?
    ) {
        if (name.isEmpty() || quantity.isEmpty()) {
            Toast.makeText(requireActivity(), R.string.fill_data, Toast.LENGTH_SHORT)
                    .show()
            return
        }

        when (ingredientOperation) {
            IngredientOperation.Add -> {
                val ingredient = Ingredient(name, quantity)
                adapter.getIngredientsList().add(ingredient)
                viewModel.ingredientsList.addAll(adapter.getIngredientsList())
            }
            IngredientOperation.Edit -> {
                val index = adapter.getIngredientsList().indexOf(ingredientToUpdate)
                adapter.getIngredientsList().removeAt(index)

                val ingredient = Ingredient(name, quantity)
                adapter.getIngredientsList().add(index, ingredient)
            }
        }

        manageAddFirstIngredientInfoVisibility()
        adapter.notifyDataSetChanged()
        (activity as EditRecipeActivity).closeKeyboard()
    }

    private fun addIngredient() {
        addOrEditIngredient(IngredientOperation.Add, null)
    }

    private fun editIngredient(ingredient: Ingredient) {
        addOrEditIngredient(IngredientOperation.Edit, ingredient)
    }

    private fun deleteIngredient(ingredient: Ingredient) {
        AlertDialog.Builder(requireActivity()).apply {
            setMessage(resources.getString(R.string.ingredient_removal_confirmation))
            setPositiveButton(resources.getString(R.string.yes)) { _, _ ->
                adapter.getIngredientsList().remove(ingredient)
                adapter.notifyDataSetChanged()
                manageAddFirstIngredientInfoVisibility()
            }
            setNegativeButton(resources.getString(R.string.cancel)) { _, _-> }
            create().show()
        }
    }

    private fun manageAddFirstIngredientInfoVisibility() {
        if (adapter.getIngredientsList().isEmpty()) {
            binding.addFirstIngredientInfoTextView.visibility = View.VISIBLE
        } else {
            binding.addFirstIngredientInfoTextView.visibility = View.INVISIBLE
        }
    }

}