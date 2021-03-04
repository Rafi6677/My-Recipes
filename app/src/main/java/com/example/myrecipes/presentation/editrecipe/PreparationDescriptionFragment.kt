package com.example.myrecipes.presentation.editrecipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myrecipes.R

class PreparationDescriptionFragment : Fragment() {

    companion object {
        fun getInstance(): PreparationDescriptionFragment {
            return PreparationDescriptionFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_preparation_description, container, false)
    }

}