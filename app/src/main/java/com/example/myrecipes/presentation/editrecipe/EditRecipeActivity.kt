package com.example.myrecipes.presentation.editrecipe

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import com.example.myrecipes.R
import com.example.myrecipes.databinding.ActivityEditRecipeBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class EditRecipeActivity : AppCompatActivity() {

    private val viewModel by viewModels<EditRecipeViewModel>()
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

}