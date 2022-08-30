package com.hcdisat.globotour

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.navigation.NavigationView
import com.hcdisat.globotour.city.VacationSpots
import com.hcdisat.globotour.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    private val mainActivityBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mainActivityBinding.root)

        // Get NavHostFragment and NavController
        val navHostFrag =
            supportFragmentManager.findFragmentById(R.id.nav_host_frag) as NavHostFragment
        navController = navHostFrag.navController

        setUpNavigationDrawer()
    }

    private fun setUpNavigationDrawer() {
        // appBarConfiguration
        val topLevelDestinations = setOf(R.id.fragmentCityList, R.id.fragmentFavoriteList)
        val appBarConfiguration = AppBarConfiguration(topLevelDestinations)

        // toolbar
        mainActivityBinding.activityMainToolbar
            .setupWithNavController(navController, appBarConfiguration)

        // connect navigationView
        mainActivityBinding.bottomNavView.setupWithNavController(navController)
    }
}