package com.example.coordinatorlayoutdemo.routers

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.coordinatorlayoutdemo.HomeScreenActivity
import com.example.coordinatorlayoutdemo.R
import com.example.coordinatorlayoutdemo.ui.RecipeDetailsFragment

fun HomeScreenActivity.goToHomeScreen() {
    supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
}