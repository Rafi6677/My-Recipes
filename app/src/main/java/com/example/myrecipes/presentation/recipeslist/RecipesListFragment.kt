package com.example.myrecipes.presentation.recipeslist

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
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
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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
        setSearchView()
    }

    override fun onResume() {
        super.onResume()
        displayRecipes()
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

    private fun setSearchView() {
        binding.recipesSearchView.apply {
            setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    showProgressBar()
                    displaySearchedRecipes("%${query.toString()}%")
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    MainScope().launch {
                        showProgressBar()
                        delay(100)
                        displaySearchedRecipes("%${newText.toString()}%")
                    }
                    return false
                }
            })
            setOnCloseListener {
                displayRecipes()
                false
            }
        }
    }

    private fun displayRecipes() {
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

    private fun displaySearchedRecipes(searchQuery: String) {
        viewModel.searchRecipes(categoryId, searchQuery)

        viewModel.searchedRecipesList.observe(viewLifecycleOwner, { recipes ->
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