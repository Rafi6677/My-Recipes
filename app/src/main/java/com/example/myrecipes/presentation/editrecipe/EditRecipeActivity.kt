package com.example.myrecipes.presentation.editrecipe

import android.os.Bundle
import android.widget.RadioButton
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.myrecipes.R
import com.example.myrecipes.data.db.model.Category
import com.example.myrecipes.data.db.model.Recipe
import com.example.myrecipes.databinding.ActivityEditRecipeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.dialog_category_chooser.view.*

@AndroidEntryPoint
class EditRecipeActivity : AppCompatActivity() {

    val viewModel by viewModels<EditRecipeViewModel>()
    private lateinit var viewPagerAdapter: RecipeViewPagerAdapter
    private lateinit var binding: ActivityEditRecipeBinding
    private lateinit var recipeTitle: String
    lateinit var recipeOperationType: RecipeOperationType

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEditRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        supportActionBar!!.hide()

        initView()
        initButtons()
    }

    private fun initView() {
        val bundle = intent.extras!!

        recipeOperationType = RecipeOperationType.setByValue(
            bundle.getInt("recipe_operation_type")
        )!!
        recipeTitle = bundle.getString("recipe_title")!!
        binding.recipeTitleEditText.setText(recipeTitle)

        initViewPager()
    }

    private fun initViewPager() {
        viewPagerAdapter = RecipeViewPagerAdapter(supportFragmentManager).apply {
            addFragment(
                    IngredientsFragment.getInstance(),
                    resources.getString(R.string.ingredients)
            )
            addFragment(
                    PreparationDescriptionFragment.getInstance(),
                    resources.getString(R.string.preparation_description)
            )
        }

        binding.editRecipeViewPager.adapter = viewPagerAdapter
        binding.editRecipeTabLayout.setupWithViewPager(binding.editRecipeViewPager)
    }

    private fun initButtons() {
        binding.categoryButton.setOnClickListener {
            var categoryId: Int? = viewModel.categoryId
            if (viewModel.categoryId == 0) categoryId = null

            showCategoryDialog(categoryId)
        }
        binding.saveButton.setOnClickListener {

        }
    }

    private fun showCategoryDialog(chosenCategoryId: Int?) {
        val dialogLayout = layoutInflater.inflate(R.layout.dialog_category_chooser, null).apply {
            category_radioGroup.setOnCheckedChangeListener { _, checkedId ->
                val categoryId = viewModel.mapDialogInterfaceCategoryToCategoryId(checkedId)
                viewModel.categoryId = categoryId
                binding.recipeCategoryTextView.text = viewModel.getCategoryName(
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

    private fun saveRecipe(recipe: Recipe) {

    }

}