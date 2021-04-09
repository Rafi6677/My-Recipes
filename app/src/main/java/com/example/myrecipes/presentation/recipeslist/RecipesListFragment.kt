package com.example.myrecipes.presentation.recipeslist

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myrecipes.R
import com.example.myrecipes.data.db.model.Category
import com.example.myrecipes.data.db.model.Recipe
import com.example.myrecipes.data.util.CategoryConversions
import com.example.myrecipes.databinding.FragmentRecipesListBinding
import com.example.myrecipes.presentation.editrecipe.EditRecipeActivity
import com.example.myrecipes.presentation.editrecipe.RecipeOperationType
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipesListFragment : Fragment() {

    private lateinit var binding: FragmentRecipesListBinding
    private lateinit var recipesAdapter: RecipesAdapter
    private var categoryId: Int = 0
    private val viewModel by viewModels<RecipesViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_recipes_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentRecipesListBinding.bind(view)
        categoryId = requireArguments().getInt("category_id")

        initView()
    }

    override fun onResume() {
        super.onResume()
        refreshRecyclerView()
    }

    private fun initView() {
        if (categoryId == -1) {
            binding.categoryTextView.text = resources.getString(R.string.favourite)
        } else {
            binding.categoryTextView.text = CategoryConversions.getCategoryName(
                    Category.setByCategoryId(categoryId),
                    resources
            )
        }
        binding.homeButton.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun refreshRecyclerView() {
        showProgressBar()

        recipesAdapter = RecipesAdapter { recipe ->
            onRecipeClickListener(recipe)
        }

        viewModel.getRecipesByCategoryId(categoryId)
        viewModel.recipesList.observe(viewLifecycleOwner, { recipes ->
            recipesAdapter.setRecipesList(recipes)
            manageNoRecipesInfoVisibility()
            recipesAdapter.notifyDataSetChanged()
        })

        binding.recipesRecyclerView.apply {
            adapter = recipesAdapter
            layoutManager = LinearLayoutManager(this@RecipesListFragment.context)
            hideProgressBar()
        }
    }

    private fun onRecipeClickListener(recipe: Recipe) {
        val bundle = Bundle().apply {
            putInt("recipe_operation_type", RecipeOperationType.Display.value)
            putSerializable("recipe", recipe)
        }
        val intent = Intent(activity, EditRecipeActivity::class.java).apply {
            putExtras(bundle)
        }

        startActivity(intent)
    }

    private fun showProgressBar() {
        binding.recipesProgressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.recipesProgressBar.visibility = View.INVISIBLE
    }

    private fun manageNoRecipesInfoVisibility() {
        if (recipesAdapter.getRecipesList().isEmpty()) {
            binding.noRecipesInfoTextView.visibility = View.VISIBLE
        } else {
            binding.noRecipesInfoTextView.visibility = View.INVISIBLE
        }
    }

}