package com.rifaldi.yoga.quranapp.presentation.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
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

        val appBarConfiguration = AppBarConfiguration(navController.graph)

        binding.apply {
            setSupportActionBar(toolbar)

            NavigationUI.setupActionBarWithNavController(this@MainActivity, navController, appBarConfiguration)

            findViewById<Toolbar>(R.id.toolbar).setupWithNavController(navController, appBarConfiguration)
        }
    }

    override fun subscribeListeners() {

    }

    override fun subscribeObservers() {

    }

    override fun onSupportNavigateUp(): Boolean {
//        val navController = findNavController(R.id.fragmentContainerView)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
