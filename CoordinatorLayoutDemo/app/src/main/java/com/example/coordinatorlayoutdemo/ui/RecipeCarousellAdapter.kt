package com.example.coordinatorlayoutdemo.ui

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import com.squareup.inject.assisted.dagger2.AssistedModule
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

//https://gist.github.com/manuelvicnt/5e206407f10e2ec8ed19a571a85ca28a
class RecipeCarousellAdapter @AssistedInject constructor(@Assisted fragment: RecepieCarousellFragment) : FragmentStateAdapter(fragment.childFragmentManager, fragment.lifecycle) {

    @AssistedInject.Factory
    interface Factory {
        fun create(fragment: RecepieCarousellFragment): RecipeCarousellAdapter
    }

    private val items = fragment.viewModel.tabs

    override fun getItemCount(): Int = items.size

    override fun createFragment(position: Int): Fragment {
        val item = items.entries.elementAt(position)
        return item.value
    }


}
//@see
//https://gist.github.com/manuelvicnt/5e206407f10e2ec8ed19a571a85ca28a

@AssistedModule
@Module(includes = [AssistedInject_RecipeCarousellAdapterModule::class])
@InstallIn(FragmentComponent::class)
interface RecipeCarousellAdapterModule {}
