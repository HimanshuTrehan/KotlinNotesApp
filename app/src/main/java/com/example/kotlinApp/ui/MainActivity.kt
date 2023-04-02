package com.example.kotlinApp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.lotlinApp.R

class MainActivity : AppCompatActivity() {

    private lateinit var navController:NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViews()
    }

    private fun findViews() {
        navController  = findNavController(R.id.fragmentContainerView)
        setupActionBarWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return  navController.navigateUp() || super.onSupportNavigateUp()
    }
    override fun onNavigateUp(): Boolean {
        return navController.navigateUp() || super.onNavigateUp()
    }


}