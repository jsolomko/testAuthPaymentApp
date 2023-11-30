package com.example.authpaymentapplication.app.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavHost
import com.example.authpaymentapplication.R
import com.example.authpaymentapplication.app.Singletons

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Singletons.init(applicationContext)

        val navHost = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHost
        val navController = navHost.navController
    }
}