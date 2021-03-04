package com.example.myrecipes.presentation.editrecipe

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.myrecipes.R
import com.example.myrecipes.databinding.ActivityEditRecipeBinding

class EditRecipeActivity : AppCompatActivity() {

    private lateinit var viewPagerAdapter: RecipeViewPagerAdapter
    private lateinit var binding: ActivityEditRecipeBinding
    private lateinit var recipeTitle: String

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