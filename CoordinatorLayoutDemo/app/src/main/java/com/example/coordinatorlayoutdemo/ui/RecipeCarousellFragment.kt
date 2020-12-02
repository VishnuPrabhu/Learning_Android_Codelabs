package com.example.coordinatorlayoutdemo.ui

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.coordinatorlayoutdemo.R
import com.example.coordinatorlayoutdemo.databinding.FragmentRecepieCarousellBinding
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RecepieCarousellFragment : Fragment() {

    @Inject lateinit var adapterFactory: RecipeCarousellAdapter.Factory
    val viewModel: RecipeCarousellViewModel by viewModels()


    private var _binding: FragmentRecepieCarousellBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        _binding = FragmentRecepieCarousellBinding.inflate(inflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureToolbar()
        configureViewPager()
        configureFloatingActionButton()
    }

    private fun configureToolbar() {
        requireAppCompatActivity().setSupportActionBar(binding.toolbar)
        requireAppCompatActivity().supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)
        requireAppCompatActivity().supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun configureViewPager() {
        binding.viewpager.adapter = adapterFactory.create(this)
        TabLayoutMediator(binding.tabs, binding.viewpager) { tab, position ->
            tab.text = viewModel.tabs.entries.elementAt(position).key
        }.attach()
    }

    private fun configureFloatingActionButton() {
        binding.fab.setOnClickListener {
            Snackbar.make(it, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                .setAction("Close", null).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.sample_actions, menu)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        when (AppCompatDelegate.getDefaultNightMode()) {
            AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM -> menu.findItem(R.id.menu_night_mode_system).isChecked =
                true
            AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY -> menu.findItem(R.id.menu_night_mode_auto).isChecked =
                true
            AppCompatDelegate.MODE_NIGHT_YES -> menu.findItem(R.id.menu_night_mode_night).isChecked =
                true
            AppCompatDelegate.MODE_NIGHT_NO -> menu.findItem(R.id.menu_night_mode_day).isChecked =
                true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menu_night_mode_system -> setNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            R.id.menu_night_mode_auto -> setNightMode(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY)
            R.id.menu_night_mode_night -> setNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            R.id.menu_night_mode_day -> setNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setNightMode(@AppCompatDelegate.NightMode nightMode: Int) {
        AppCompatDelegate.setDefaultNightMode(nightMode)
        requireActivity().recreate()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

fun Fragment.requireAppCompatActivity(): AppCompatActivity {
    return requireActivity() as AppCompatActivity
}