package com.example.myrecipes.presentation.editrecipe

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.res.ResourcesCompat
import com.example.myrecipes.R
import com.example.myrecipes.data.db.model.Category
import com.example.myrecipes.data.db.model.Recipe
import com.example.myrecipes.data.util.CategoryConversions
import com.example.myrecipes.databinding.ActivityEditRecipeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.dialog_category_chooser.view.*

@AndroidEntryPoint
class EditRecipeActivity : AppCompatActivity() {

    val viewModel by viewModels<EditRecipeViewModel>()
    var recipe: Recipe ?= null
    lateinit var recipeOperationType: RecipeOperationType
    private lateinit var viewPagerAdapter: RecipeViewPagerAdapter
    private lateinit var binding: ActivityEditRecipeBinding
    private lateinit var recipeTitle: String
    private lateinit var ingredientsFragment: IngredientsFragment
    private lateinit var preparationDescriptionFragment: PreparationDescriptionFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEditRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        supportActionBar!!.hide()

        initView()
        initButtons()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    private fun initView() {
        val bundle = intent.extras!!

        recipeOperationType = RecipeOperationType.setByValue(
            bundle.getInt("recipe_operation_type")
        )!!

        initViewPager()

        when (recipeOperationType) {
            RecipeOperationType.Add -> {
                recipe = null
                setRecipeTitle(bundle.getString("recipe_title")!!)
                binding.recipeTitleEditText.isFocusable = true
                binding.categoryButton.visibility = View.VISIBLE
                binding.favoriteButton.visibility = View.INVISIBLE
                binding.deleteButton.visibility = View.INVISIBLE
                binding.saveButton.setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.ic_save, this.theme))
            }
            RecipeOperationType.Edit -> {
                prepareRecipe(bundle.getSerializable("recipe")!! as Recipe)
                binding.recipeTitleEditText.isFocusable = true
                binding.categoryButton.visibility = View.VISIBLE
                binding.favoriteButton.visibility = View.VISIBLE
                binding.deleteButton.visibility = View.VISIBLE
                binding.saveButton.setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.ic_save, this.theme))
            }
            RecipeOperationType.Display -> {
                prepareRecipe(bundle.getSerializable("recipe")!! as Recipe)
                binding.recipeTitleEditText.isFocusable = false
                binding.categoryButton.visibility = View.INVISIBLE
                binding.favoriteButton.visibility = View.VISIBLE
                binding.deleteButton.visibility = View.GONE
                binding.saveButton.setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.ic_edit, this.theme))
            }
        }
    }

    private fun initViewPager() {
        ingredientsFragment = IngredientsFragment.getInstance()
        preparationDescriptionFragment = PreparationDescriptionFragment.getInstance()

        viewPagerAdapter = RecipeViewPagerAdapter(supportFragmentManager).apply {
            addFragment(
                ingredientsFragment,
                resources.getString(R.string.ingredients)
            )
            addFragment(
                preparationDescriptionFragment,
                resources.getString(R.string.preparation_description)
            )
        }

        binding.editRecipeViewPager.adapter = viewPagerAdapter
        binding.editRecipeTabLayout.setupWithViewPager(binding.editRecipeViewPager)
    }

    private fun prepareRecipe(bundleRecipe: Recipe) {
        recipe = bundleRecipe
        setRecipeTitle(recipe!!.title)
        setCategory(recipe!!.categoryId)

        if (recipe!!.isFavourite) {
            binding.favoriteButton.setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.ic_favorite, this.theme))
        } else {
            binding.favoriteButton.setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.ic_favorite_border, this.theme))
        }
    }

    private fun setRecipeTitle(title: String) {
        recipeTitle = title
        binding.recipeTitleEditText.setText(recipeTitle)
    }

    private fun setCategory(categoryId: Int) {
        binding.recipeCategoryTextView.text = CategoryConversions.getCategoryName(
            Category.setByCategoryId(categoryId),
            resources
        )
    }

    private fun initButtons() {
        binding.categoryButton.setOnClickListener {
            var categoryId: Int? = viewModel.categoryId
            if (viewModel.categoryId == 0) categoryId = null

            showCategoryDialog(categoryId)
        }
        binding.favoriteButton.setOnClickListener {
            if (recipe == null) {
                return@setOnClickListener
            }

            recipe!!.let {
                recipe = viewModel.changeRecipeIsFavorite(it)

                if (it.isFavourite) {
                    binding.favoriteButton.setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.ic_favorite_border, this.theme))

                    Toast.makeText(this, resources.getString(R.string.recipe_removed_from_favorites), Toast.LENGTH_SHORT)
                            .show()
                } else {
                    binding.favoriteButton.setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.ic_favorite, this.theme))

                    Toast.makeText(this, resources.getString(R.string.recipe_added_to_favorites), Toast.LENGTH_SHORT)
                            .show()
                }
            }
        }
        binding.saveButton.setOnClickListener {
            viewModel.ingredientsList = ingredientsFragment.adapter.getIngredientsList()
            viewModel.preparationDescription = preparationDescriptionFragment.getPreparationDescription()

            if (viewModel.ingredientsList.isEmpty()
                || viewModel.preparationDescription.isEmpty()
                || viewModel.categoryId == 0
                || binding.recipeTitleEditText.text.toString().isEmpty()
            ) {
                Toast.makeText(this, resources.getString(R.string.fill_data), Toast.LENGTH_SHORT)
                        .show()
                return@setOnClickListener
            }

            viewModel.saveRecipe(binding.recipeTitleEditText.text.toString())

            Toast.makeText(this, resources.getString(R.string.recipe_added), Toast.LENGTH_SHORT)
                    .show()
            finish()
        }
    }

    private fun showCategoryDialog(chosenCategoryId: Int?) {
        val dialogLayout = layoutInflater.inflate(R.layout.dialog_category_chooser, null).apply {
            category_radioGroup.setOnCheckedChangeListener { _, checkedId ->
                val categoryId = CategoryConversions.mapDialogInterfaceCategoryToCategoryId(checkedId)
                viewModel.categoryId = categoryId
                binding.recipeCategoryTextView.text = CategoryConversions.getCategoryName(
                    Category.setByCategoryId(categoryId),
                    resources
                )
            }

            if (chosenCategoryId != null) {
                val radioButton = category_radioGroup.getChildAt(chosenCategoryId - 1) as RadioButton
                radioButton.isChecked = true
            }
        }

        AlertDialog.Builder(this).apply {
            setTitle(resources.getString(R.string.choose_category))
            setView(dialogLayout)
            setPositiveButton(resources.getString(R.string.ok)) { _, _ -> }
        }.show()
    }

    fun closeKeyboard() {
        val view: View? = this.currentFocus

        if (view != null) {
            val manager: InputMethodManager = getSystemService(
                    Context.INPUT_METHOD_SERVICE) as InputMethodManager
            manager.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

}