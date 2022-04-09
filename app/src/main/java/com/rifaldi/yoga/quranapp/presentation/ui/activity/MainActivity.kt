package com.rifaldi.yoga.quranapp.presentation.ui.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.rifaldi.yoga.quranapp.R
import com.rifaldi.yoga.quranapp.databinding.ActivityMainBinding
import com.rifaldi.yoga.quranapp.presentation.ui.base.BaseInterface
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), BaseInterface {
    
    private lateinit var binding : ActivityMainBinding

    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initComponents()
        subscribeListeners()
        subscribeObservers()
    }


    override fun initComponents() {
        navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController


        binding.apply {
            setSupportActionBar(toolbar)
            val appBarConfiguration = AppBarConfiguration(navController.graph)
            NavigationUI.setupActionBarWithNavController(this@MainActivity, navController, appBarConfiguration)
            findViewById<Toolbar>(R.id.toolbar).setupWithNavController(navController, appBarConfiguration)
            findViewById<BottomNavigationView>(R.id.bottom_nav).setupWithNavController(navController)
        }
    }

    override fun subscribeListeners() {
        binding.apply {

            navController.addOnDestinationChangedListener { _, destination, _ ->
                if(destination.id == R.id.homeFragment || destination.id == R.id.bookmarkFragment) {
                    bottomNav.visibility = View.VISIBLE
                } else {
                    bottomNav.visibility = View.GONE
                }
            }
        }
    }

    override fun subscribeObservers() {

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragmentContainerView)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }


}
