package com.example.coordinatorlayoutdemo.routers

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.coordinatorlayoutdemo.R
import com.example.coordinatorlayoutdemo.ui.RecipeDetailsFragment

fun Fragment.navigateToRecipeDetails(recipe: String) {
    val detailsFragment = RecipeDetailsFragment()
    detailsFragment.arguments = bundleOf("EXTRA_NAME" to recipe)
    requireActivity().supportFragmentManager.beginTransaction()
        .add(R.id.fragment_container, detailsFragment)
//        .setCustomAnimations()
        .addToBackStack(null)
        .commit()
}