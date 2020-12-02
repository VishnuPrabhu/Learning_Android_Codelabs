package com.example.coordinatorlayoutdemo

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.example.coordinatorlayoutdemo.databinding.ActivityHomeScreenBinding
import com.example.coordinatorlayoutdemo.routers.goToHomeScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configureNavigationDrawer()
    }

    private fun configureNavigationDrawer() {
        binding.navView.setNavigationItemSelectedListener {
            it.isChecked = true
            binding.drawerLayout.closeDrawers()

            when (it.itemId) {
                R.id.nav_home -> { goToHomeScreen() }
            }
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> {
                binding.drawerLayout.openDrawer(GravityCompat.START)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}