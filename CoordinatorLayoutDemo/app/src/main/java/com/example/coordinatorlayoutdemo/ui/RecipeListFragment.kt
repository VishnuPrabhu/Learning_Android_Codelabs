package com.example.coordinatorlayoutdemo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coordinatorlayoutdemo.databinding.FragmentRecepieListBinding
import com.example.coordinatorlayoutdemo.routers.navigateToRecipeDetails
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RecipeListFragment : Fragment() {

    @Inject lateinit var adapter: RecipeListAdapter.Factory
    private val viewModel: RecipeCarousellViewModel by viewModels()

    private lateinit var binding: FragmentRecepieListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecepieListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recipeList.layoutManager = LinearLayoutManager(requireContext())
        binding.recipeList.adapter = adapter.create(viewModel, ::onClick)
    }

    private fun onClick(v: View) {
        navigateToRecipeDetails(v.tag.toString())
    }
}