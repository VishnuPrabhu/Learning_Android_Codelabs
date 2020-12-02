package com.example.coordinatorlayoutdemo.ui

import androidx.fragment.app.Fragment
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.coordinatorlayoutdemo.data.Recipes
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.FragmentScoped
import java.util.*

@FragmentScoped
class RecipeCarousellViewModel @ViewModelInject constructor(val tabs: LinkedHashMap<String, Fragment>) : ViewModel() {

    val recipesList get() = getRandomSublist(Recipes.sRecipeStrings, 30)

    private fun getRandomSublist(array: Array<String>, amount: Int): List<String> {
        val list = ArrayList<String>(amount)
        val random = Random()
        while (list.size < amount) {
            list.add(array[random.nextInt(array.size)])
        }
        return list
    }
}

@Module
@InstallIn(ActivityRetainedComponent::class)
object RecipeCarousellViewModelDataStore {

    @Provides
    fun tabData(): LinkedHashMap<String, Fragment> {
        val map = linkedMapOf<String, Fragment>()
        map.put("Category 1", RecipeListFragment())
        map.put("Category 2", RecipeListFragment())
        map.put("Category 3", RecipeListFragment())
        return map
    }
}