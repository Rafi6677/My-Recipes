package com.example.myrecipes.presentation.editrecipe

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.example.myrecipes.R
import com.example.myrecipes.data.db.model.Recipe
import com.example.myrecipes.databinding.FragmentPreparationDescriptionBinding

class PreparationDescriptionFragment : Fragment() {

    private lateinit var binding: FragmentPreparationDescriptionBinding
    private lateinit var viewModel: EditRecipeViewModel
    private var recipe: Recipe? = null

    companion object {
        fun getInstance(): PreparationDescriptionFragment {
            return PreparationDescriptionFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_preparation_description, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentPreparationDescriptionBinding.bind(view)
        viewModel = (activity as EditRecipeActivity).viewModel
        recipe = (activity as EditRecipeActivity).recipe

        initData()
    }

    private fun initData() {
        if ((activity as EditRecipeActivity).recipeOperationType == RecipeOperationType.Display) {
            binding.preparationDescriptionEditText.apply {
                setText(recipe!!.preparationDescription)
                isFocusable = false
            }
        }
    }

    fun getPreparationDescription(): String {
        return binding.preparationDescriptionEditText.text.toString()
    }

}