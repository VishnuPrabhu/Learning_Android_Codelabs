package com.example.coordinatorlayoutdemo.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.coordinatorlayoutdemo.R
import com.example.coordinatorlayoutdemo.data.Recipes
import com.example.coordinatorlayoutdemo.databinding.FragmentRecipeDetailsBinding

class RecipeDetailsFragment : Fragment() {

    private lateinit var binding: FragmentRecipeDetailsBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        binding = FragmentRecipeDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureToolbar()
        loadBackdrop()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.sample_actions, menu)
    }

    private fun configureToolbar() {
        requireAppCompatActivity().setSupportActionBar(binding.toolbar)
        requireAppCompatActivity().supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val title = arguments?.getString("EXTRA_NAME")
        binding.collapseToolbar.title = title
    }

    private fun loadBackdrop() {
        Glide.with(requireContext())
            .load(Recipes.randomCheeseDrawable)
            .apply(RequestOptions.centerCropTransform())
            .into(binding.backdrop)
    }
}