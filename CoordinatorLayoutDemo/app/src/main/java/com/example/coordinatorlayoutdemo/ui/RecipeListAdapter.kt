package com.example.coordinatorlayoutdemo.ui

import android.content.Context
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.coordinatorlayoutdemo.R
import com.example.coordinatorlayoutdemo.data.Recipes
import com.example.coordinatorlayoutdemo.databinding.ListItemBinding
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import dagger.hilt.android.qualifiers.ActivityContext

class RecipeListAdapter @AssistedInject constructor(@ActivityContext val context: Context,
                                                    @Assisted val onClick: View.OnClickListener,
                                                    @Assisted val viewModel: RecipeCarousellViewModel) : RecyclerView.Adapter<Recipe>() {

    @AssistedInject.Factory
    interface Factory {
        fun create(viewModel: RecipeCarousellViewModel, onClick: View.OnClickListener): RecipeListAdapter
    }

    private val itemBackground: Int
    init {
        val background = TypedValue()
        context.theme.resolveAttribute(R.attr.selectableItemBackground, background, true)
        itemBackground = background.resourceId
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Recipe {
        val itemView = ListItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return Recipe(itemView, onClick)
    }

    override fun onBindViewHolder(holder: Recipe, position: Int) {
        val item = viewModel.recipesList[position]
        holder.boundedString = item
        holder.binding.text1.text = item

        val requestOptions = RequestOptions()
        Glide.with(context)
            .load(Recipes.randomCheeseDrawable)
            .apply(requestOptions.fitCenter())
            .into(holder.binding.avatar)
    }

    override fun getItemCount(): Int {
        return viewModel.recipesList.size
    }
}

class Recipe(val binding: ListItemBinding, val clickListener: View.OnClickListener) : RecyclerView.ViewHolder(binding.root) {
    var boundedString = ""

    init {
        itemView.setOnClickListener {
            it.tag = boundedString
            clickListener.onClick(it)
        }
    }
}