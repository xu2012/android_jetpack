package com.xu.navigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import kotlinx.android.synthetic.main.activity_navigation.*

class NavigationActivity : AppCompatActivity() {
    lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        navController = findNavController(R.id.rootFragment)
//        NavigationUI.setupActionBarWithNavController(this,navController)
        toolbar.setNavigationOnClickListener {
            super.onBackPressed()
        }
    }

   /* override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }*/
}