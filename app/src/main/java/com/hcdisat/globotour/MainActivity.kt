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
        val appBarConfiguration = AppBarConfiguration(
            navController.graph,
            mainActivityBinding.drawerLayout
        )

        // toolbar
        mainActivityBinding.activityMainToolbar
            .setupWithNavController(navController, appBarConfiguration)

        // connect navigationView
        mainActivityBinding.navView.setupWithNavController(navController)

        registerBackPressedCallBack()
    }

    private fun handleBackPressed() = when {
        mainActivityBinding.drawerLayout.isOpen -> mainActivityBinding.drawerLayout.close()
        else -> finish()
    }

    private fun registerBackPressedCallBack() {
        onBackPressedDispatcher.addCallback(this, object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                handleBackPressed()
            }
        })
    }
}