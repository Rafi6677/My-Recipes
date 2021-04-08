package com.example.myrecipes.presentation.home

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.myrecipes.R
import com.example.myrecipes.data.db.model.Category
import com.example.myrecipes.databinding.FragmentHomeBinding
import com.example.myrecipes.presentation.editrecipe.EditRecipeActivity
import com.example.myrecipes.presentation.editrecipe.RecipeOperationType
import kotlinx.android.synthetic.main.dialog_recipe_creation.view.*

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        initButtons()
    }

    private fun initButtons() {
        binding.addRecipeButton.setOnClickListener {
            showAddRecipeDialog()
        }

        binding.favouriteMealCardView.setOnClickListener {
            val bundle = Bundle().apply {
                putInt("category_id", -1)
            }
            findNavController().navigate(
                R.id.action_homeFragment_to_recipesListFragment,
                bundle
            )
        }

        binding.breakfastCardView.setOnClickListener {
            val bundle = Bundle().apply {
                putInt("category_id", Category.Breakfast.categoryId)
            }
            findNavController().navigate(
                R.id.action_homeFragment_to_recipesListFragment,
                bundle
            )
        }

        binding.soupCardView.setOnClickListener {
            val bundle = Bundle().apply {
                putInt("category_id", Category.Soup.categoryId)
            }
            findNavController().navigate(
                R.id.action_homeFragment_to_recipesListFragment,
                bundle
            )
        }

        binding.mainDishCardView.setOnClickListener {
            val bundle = Bundle().apply {
                putInt("category_id", Category.MainDish.categoryId)
            }
            findNavController().navigate(
                R.id.action_homeFragment_to_recipesListFragment,
                bundle
            )
        }

        binding.saladCardView.setOnClickListener {
            val bundle = Bundle().apply {
                putInt("category_id", Category.Salad.categoryId)
            }
            findNavController().navigate(
                R.id.action_homeFragment_to_recipesListFragment,
                bundle
            )
        }

        binding.snackCardView.setOnClickListener {
            val bundle = Bundle().apply {
                putInt("category_id", Category.Snack.categoryId)
            }
            findNavController().navigate(
                R.id.action_homeFragment_to_recipesListFragment,
                bundle
            )
        }

        binding.dessertCardView.setOnClickListener {
            val bundle = Bundle().apply {
                putInt("category_id", Category.Dessert.categoryId)
            }
            findNavController().navigate(
                R.id.action_homeFragment_to_recipesListFragment,
                bundle
            )
        }

        binding.drinkCardView.setOnClickListener {
            val bundle = Bundle().apply {
                putInt("category_id", Category.Drinks.categoryId)
            }
            findNavController().navigate(
                R.id.action_homeFragment_to_recipesListFragment,
                bundle
            )
        }
    }

    private fun showAddRecipeDialog() {
        val dialogLayout = layoutInflater.inflate(R.layout.dialog_recipe_creation, null)

        AlertDialog.Builder(requireActivity()).apply {
            setTitle(R.string.add_recipe)
            setView(dialogLayout)
            setPositiveButton(R.string.next) { _, _ ->
                tryCreateRecipe(
                    dialogLayout.recipe_name_editText.text.toString(),
                )
            }
            setNegativeButton(R.string.cancel) { _, _ -> }
            create().show()
        }
    }

    private fun tryCreateRecipe(recipeName: String) {
        if (recipeName.isEmpty()) {
            Toast.makeText(requireActivity(), R.string.fill_data, Toast.LENGTH_SHORT)
                    .show()
            return
        }

        val bundle = Bundle().apply {
            putInt("recipe_operation_type", RecipeOperationType.Add.value)
            putString("recipe_title", recipeName)
        }
        val intent = Intent(activity, EditRecipeActivity::class.java).apply {
            putExtras(bundle)
        }

        startActivity(intent)

    }

}